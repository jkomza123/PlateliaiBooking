package com.example.demo.dao;

import com.example.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao extends JpaRepository<Booking, Long> {
    @Query("""
        SELECT b FROM Booking b
        WHERE b.room.id = :id
        AND b.dateFrom <= :to
        AND b.dateTo >= :from
    """)
    List<Booking> findBookingsByRoom(
            @Param("id") Long id,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}
