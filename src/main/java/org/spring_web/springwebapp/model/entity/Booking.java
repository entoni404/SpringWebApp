package org.spring_web.springwebapp.model.entity;


import jakarta.persistence.*;
import org.spring_web.springwebapp.model.enums.BookingStatus;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public Booking() {
    }

    public Booking(Long id, Date bookingDate, BookingStatus status, User user, Flight flight) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.status = status;
        this.user = user;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "status=" + status +
                ", bookingDate=" + bookingDate +
                ", id=" + id +
                '}';
    }
}
