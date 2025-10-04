package com.example.demo.dao;

import com.example.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDao extends JpaRepository<Booking, Long> { }
