package org.spring_web.springwebapp.model.mapper;

import org.spring_web.springwebapp.model.entity.User;
import org.spring_web.springwebapp.model.resource.UserResource;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final UserDetailsMapper userDetailsMapper;

    public UserMapper(UserDetailsMapper userDetailsMapper) {
        this.userDetailsMapper = userDetailsMapper;
    }

    public UserResource toDTO(User user) {
        return new UserResource(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getUserDetails() != null ? userDetailsMapper.toDTO(user.getUserDetails()) : null
        );
    }

    public List<UserResource> toDTOList(List<User> users) {
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

