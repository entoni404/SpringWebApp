package org.spring_web.springwebapp.repository;

import org.spring_web.springwebapp.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findFlightsByDepartureDateAndOrigin(LocalDate departureDate, String origin);


}
