package org.spring_web.springwebapp.model.mapper;

import org.spring_web.springwebapp.model.entity.UserDetails;
import org.spring_web.springwebapp.model.resource.UserDetailsResource;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsMapper {

    public UserDetailsResource toDTO(UserDetails userDetails) {
        return new UserDetailsResource(
                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                userDetails.getPhoneNumber(),
                userDetails.getUser().getId()
        );
    }

    public List<UserDetailsResource> toDTOList(List<UserDetails> userDetailsList) {
        return userDetailsList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
