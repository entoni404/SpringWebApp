package org.spring_web.springwebapp.service;

import org.spring_web.springwebapp.model.entity.User;
import org.spring_web.springwebapp.model.mapper.UserMapper;
import org.spring_web.springwebapp.model.resource.UserResource;
import org.spring_web.springwebapp.repository.FlightRepository;
import org.spring_web.springwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FlightRepository flightRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, FlightRepository flightRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.flightRepository = flightRepository;
    }

    public List<UserResource> getAllUsers() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    public UserResource getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public List<UserResource> getUsersByFlight(Long flightId) {
        if (!flightRepository.existsById(flightId)) {
            throw new RuntimeException("Flight not found with ID: " + flightId);
        }
        return userMapper.toDTOList(userRepository.findUsersByFlight(flightId));
    }

    @Transactional
    public UserResource saveUser(User user) {
        if (user.getUserDetails() != null) {
            user.getUserDetails().setUser(user); // Ensure UserDetails is properly linked
        }
        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResource updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());

        if (updatedUser.getUserDetails() != null) {
            user.getUserDetails().setFirstName(updatedUser.getUserDetails().getFirstName());
            user.getUserDetails().setLastName(updatedUser.getUserDetails().getLastName());
            user.getUserDetails().setEmail(updatedUser.getUserDetails().getEmail());
        }

        return userMapper.toDTO(userRepository.save(user));
    }

}
