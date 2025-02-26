package org.spring_web.springwebapp.model.mapper;


import org.spring_web.springwebapp.model.entity.Booking;
import org.spring_web.springwebapp.model.resource.BookingResource;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    public BookingResource toDTO(Booking booking) {
        return new BookingResource(
                booking.getStatus(),
                booking.getBookingDate().toString(),
                booking.getUser().getId(),
                booking.getFlight().getId()
        );
    }

    public List<BookingResource> toDTOList(List<Booking> bookings) {
        return bookings.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
