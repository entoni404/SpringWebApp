package org.spring_web.springwebapp.controller;

import org.spring_web.springwebapp.model.entity.Flight;
import org.spring_web.springwebapp.model.resource.FlightResource;
import org.spring_web.springwebapp.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightResource>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResource> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightResource>> getFlightsByDeparture(@RequestParam LocalDate departureDate, @RequestParam String origin) {
        return ResponseEntity.ok(flightService.getFlightsByDeparture(departureDate, origin));
    }

    @PostMapping
    public ResponseEntity<?> createFlight(@RequestBody Flight flight) {
        try {
            return ResponseEntity.ok(flightService.saveFlight(flight));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody Flight updatedFlight) {
        try {
            return ResponseEntity.ok(flightService.updateFlight(id, updatedFlight));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}

