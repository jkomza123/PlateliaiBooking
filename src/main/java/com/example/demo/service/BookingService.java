package com.example.demo.service;

import com.example.demo.dto.BookingDetails;
import com.example.demo.dto.StripeResponseDetails;

import java.util.List;

public interface BookingService  {

    List<BookingDetails> findAll();

    List<BookingDetails> findBookingsByRoom(Long id, String from, String to);

    BookingDetails findById(Long id);

    StripeResponseDetails createBookingAndCheckout(BookingDetails bookingDetails);

    void expireBooking(Long bookingId);

    void confirmBooking(Long bookingId);

    void cancelBooking(Long id, String token);
}
