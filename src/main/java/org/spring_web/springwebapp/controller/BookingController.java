package org.spring_web.springwebapp.controller;

import org.spring_web.springwebapp.model.entity.Booking;
import org.spring_web.springwebapp.model.resource.BookingResource;
import org.spring_web.springwebapp.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingResource>> getAllBookingsOrdered() {
        return ResponseEntity.ok(bookingService.getAllBookingsOrdered());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResource>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<BookingResource>> getBookingsByFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(bookingService.getBookingsByFlight(flightId));
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            return ResponseEntity.ok(bookingService.saveBooking(booking));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        try {
            return ResponseEntity.ok(bookingService.updateBooking(id, updatedBooking));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}