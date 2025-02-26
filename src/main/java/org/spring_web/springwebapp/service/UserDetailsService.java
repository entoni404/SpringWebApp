package org.spring_web.springwebapp.service;

import org.spring_web.springwebapp.model.entity.UserDetails;
import org.spring_web.springwebapp.model.mapper.UserDetailsMapper;
import org.spring_web.springwebapp.model.resource.UserDetailsResource;
import org.spring_web.springwebapp.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsMapper userDetailsMapper;

    public UserDetailsService(UserDetailsRepository userDetailsRepository, UserDetailsMapper userDetailsMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    public List<UserDetailsResource> getAllUserDetails() {
        return userDetailsMapper.toDTOList(userDetailsRepository.findAll());
    }

    public UserDetailsResource getUserDetailsById(Long id) {
        return userDetailsRepository.findById(id)
                .map(userDetailsMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User details not found with ID: " + id));
    }

    @Transactional
    public UserDetailsResource saveUserDetails(UserDetails userDetails) {
        return userDetailsMapper.toDTO(userDetailsRepository.save(userDetails));
    }

    @Transactional
    public void deleteUserDetails(Long id) {
        if (!userDetailsRepository.existsById(id)) {
            throw new RuntimeException("User details not found with ID: " + id);
        }
        userDetailsRepository.deleteById(id);
    }

    @Transactional
    public UserDetailsResource updateUserDetails(Long id, UserDetails updatedDetails) {
        UserDetails userDetails = userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User details not found with ID: " + id));

        userDetails.setFirstName(updatedDetails.getFirstName());
        userDetails.setLastName(updatedDetails.getLastName());
        userDetails.setEmail(updatedDetails.getEmail());
        userDetails.setPhoneNumber(updatedDetails.getPhoneNumber());

        return userDetailsMapper.toDTO(userDetailsRepository.save(userDetails));
    }

}