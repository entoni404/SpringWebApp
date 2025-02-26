package org.spring_web.springwebapp.service;

import org.spring_web.springwebapp.model.entity.Flight;
import org.spring_web.springwebapp.model.mapper.FlightMapper;
import org.spring_web.springwebapp.model.resource.FlightResource;
import org.spring_web.springwebapp.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public List<FlightResource> getAllFlights() {
        return flightMapper.toDTOList(flightRepository.findAll());
    }

    public FlightResource getFlightById(Long id) {
        return flightRepository.findById(id)
                .map(flightMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
    }

    public List<FlightResource> getFlightsByDeparture(LocalDate departureDate, String origin) {
        return flightMapper.toDTOList(flightRepository.findFlightsByDepartureDateAndOrigin(departureDate, origin));
    }

    @Transactional
    public FlightResource saveFlight(Flight flight) {
        if (flight.getFlightStatus() == null) {
            throw new RuntimeException("Flight status cannot be null.");
        }
        return flightMapper.toDTO(flightRepository.save(flight));
    }


    @Transactional
    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new RuntimeException("Flight not found with ID: " + id);
        }
        flightRepository.deleteById(id);
    }

    @Transactional
    public FlightResource updateFlight(Long id, Flight updatedFlight) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));

        flight.setOrigin(updatedFlight.getOrigin());
        flight.setDestination(updatedFlight.getDestination());
        flight.setAirline(updatedFlight.getAirline());
        flight.setFlightStatus(updatedFlight.getFlightStatus());

        return flightMapper.toDTO(flightRepository.save(flight));
    }

}
