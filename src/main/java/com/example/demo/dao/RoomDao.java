package com.example.demo.dao;

import com.example.demo.entity.Room;
import com.example.demo.enums.BookingStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomDao extends JpaRepository<Room, Long> {
    @Query("""
        SELECT r FROM Room r
        WHERE r.id NOT IN (
            SELECT b.room.id FROM Booking b
            WHERE b.dateFrom < :to
              AND b.dateTo > :from
              AND b.status <> :cancelled
        )
    """)
    List<Room> findAvailableRooms(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            @Param("cancelled") BookingStatusEnum cancelled
            );
}
