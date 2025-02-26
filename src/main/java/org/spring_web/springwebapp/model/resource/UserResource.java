package org.spring_web.springwebapp.model.resource;

public record UserResource(String username, String role, UserDetailsResource userDetails) {}
