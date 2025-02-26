package org.spring_web.springwebapp.model.resource;

import org.spring_web.springwebapp.model.enums.FlightStatus;

public record FlightResource(Long id, String origin, String destination,
                             String airline, FlightStatus flightStatus) {}
