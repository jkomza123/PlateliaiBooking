package com.example.demo.service;

import com.example.demo.dto.RoomDetails;
import com.example.demo.entity.Room;

import java.util.List;

public interface RoomService {

    List<RoomDetails> findAll();

    List<RoomDetails> searchRooms(String from, String to);

    RoomDetails findById(Long id);

    Room save(Room room);

    void delete(Long id);
}
