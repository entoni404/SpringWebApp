package org.spring_web.springwebapp.repository;

import org.spring_web.springwebapp.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByOrderByBookingDateDesc();

    List<Booking> findByUserId(Long userId);

    @Query("SELECT b FROM Booking b WHERE b.flight.id = :flightId AND b.flight.flightStatus <> 'CANCELLED'")
    List<Booking> findByFlightId(@Param("flightId") Long flightId);

}
