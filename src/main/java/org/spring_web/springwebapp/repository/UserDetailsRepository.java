package org.spring_web.springwebapp.repository;


import org.spring_web.springwebapp.model.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
