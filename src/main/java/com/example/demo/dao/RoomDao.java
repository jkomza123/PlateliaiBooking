package com.example.demo.dao;

import com.example.demo.entity.Room;
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
            WHERE b.startDate <= :to
              AND b.endDate >= :from
        )
    """)
    List<Room> findAvailableRooms(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}
