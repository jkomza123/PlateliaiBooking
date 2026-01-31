package com.example.demo.utils;

import com.example.demo.dto.BookingDetails;
import com.example.demo.entity.Booking;

public class BookingDtoUtils {

    private BookingDtoUtils() {
    }

    public static BookingDetails mapBookingToDetails(Booking booking) {
        if (booking == null) return null;

        BookingDetails dto = new BookingDetails();
        dto.setId(booking.getId());
        dto.setRoomId(booking.getRoom().getId());
        dto.setDateFrom(booking.getDateFrom());
        dto.setDateTo(booking.getDateTo());
        dto.setEmail(booking.getEmail());
        dto.setOwnerName(booking.getOwnerName());
        dto.setPhoneNumber(booking.getPhoneNumber());
        dto.setPrice(booking.getPrice());
        dto.setStatus(booking.getStatus());

        return dto;
    }
}