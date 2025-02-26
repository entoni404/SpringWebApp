package org.spring_web.springwebapp.model.resource;

import org.spring_web.springwebapp.model.enums.BookingStatus;

public record BookingResource(Long id, BookingStatus bookingStatus, String bookingDate, Long userId, Long flightId) {}
