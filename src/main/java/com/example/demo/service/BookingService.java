package com.example.demo.service;

import com.example.demo.entity.Booking;

import java.util.List;

public interface BookingService  {

    List<Booking> findAll();

    Booking findById(Long id);

    Booking save(Booking booking);

    void delete(Long id);
}
