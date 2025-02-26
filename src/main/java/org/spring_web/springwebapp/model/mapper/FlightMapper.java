package org.spring_web.springwebapp.model.mapper;

import org.spring_web.springwebapp.model.entity.Flight;
import org.spring_web.springwebapp.model.resource.FlightResource;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper {

    public FlightResource toDTO(Flight flight) {
        return new FlightResource(
                flight.getOrigin(),
                flight.getDestination(),
                flight.getAirline(),
                flight.getFlightStatus()
        );
    }

    public List<FlightResource> toDTOList(List<Flight> flights) {
        return flights.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
