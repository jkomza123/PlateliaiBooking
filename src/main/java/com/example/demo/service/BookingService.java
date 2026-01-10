package com.example.demo.service;

import com.example.demo.dto.BookingDetails;
import com.example.demo.entity.Booking;

import java.util.List;

public interface BookingService  {

    List<Booking> findAll();

    List<Booking> findBookingsByRoom(Long id, String from, String to);

    Booking findById(Long id);

    Booking save(BookingDetails bookingDetails);

    void delete(Long id);
}
