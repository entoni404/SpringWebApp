package org.spring_web.springwebapp.model.resource;

public record UserResource(Long id, String username, String role, UserDetailsResource userDetails) {}
