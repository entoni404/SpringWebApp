package org.spring_web.springwebapp.repository;


import org.spring_web.springwebapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u JOIN u.bookings b WHERE b.flight.id = :flightId")
    List<User> findUsersByFlight(@Param("flightId") Long flightId);

}
