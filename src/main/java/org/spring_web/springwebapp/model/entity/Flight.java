package org.spring_web.springwebapp.model.entity;

import jakarta.persistence.*;
import org.spring_web.springwebapp.model.enums.FlightStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String origin;

    @Column
    private String destination;

    @Column
    private String airline;

    @Column
    private String flightNumber;

    @Column
    private LocalDate departureDate;

    @Column
    private LocalDate arrivalDate;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus flightStatus;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Flight() {
    }

    public Flight(Long id, String origin, String destination,
                  String airline, String flightNumber, LocalDate departureDate,
                  LocalDate arrivalDate, FlightStatus flightStatus) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.flightStatus = flightStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", airline='" + airline + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", flightStatus=" + flightStatus +
                '}';
    }
}
