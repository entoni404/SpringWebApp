package org.spring_web.springwebapp.service;

import jakarta.transaction.Transactional;
import org.spring_web.springwebapp.model.entity.Booking;
import org.spring_web.springwebapp.model.mapper.BookingMapper;
import org.spring_web.springwebapp.model.resource.BookingResource;
import org.spring_web.springwebapp.repository.BookingRepository;
import org.spring_web.springwebapp.repository.FlightRepository;
import org.spring_web.springwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper, UserRepository userRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    public List<BookingResource> getAllBookingsOrdered() {
        return bookingMapper.toDTOList(bookingRepository.findAllByOrderByBookingDateDesc());
    }

    public List<BookingResource> getBookingsByUser(Long userId) {
        return bookingMapper.toDTOList(bookingRepository.findByUserId(userId));
    }

    public List<BookingResource> getBookingsByFlight(Long flightId) {
        return bookingMapper.toDTOList(bookingRepository.findByFlightId(flightId));
    }



    @Transactional
    public BookingResource saveBooking(Booking booking) {
        if (!userRepository.existsById(booking.getUser().getId())) {
            throw new RuntimeException("User not found with ID: " + booking.getUser().getId());
        }
        if (!flightRepository.existsById(booking.getFlight().getId())) {
            throw new RuntimeException("Flight not found with ID: " + booking.getFlight().getId());
        }
        return bookingMapper.toDTO(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResource updateBooking(Long id, Booking updatedBooking) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));

        booking.setStatus(updatedBooking.getStatus());
        booking.setBookingDate(updatedBooking.getBookingDate());

        return bookingMapper.toDTO(bookingRepository.save(booking));
    }

    @Transactional
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with ID: " + id);
        }
        bookingRepository.deleteById(id);
    }




}
