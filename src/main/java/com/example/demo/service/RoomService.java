package com.example.demo.service;

import com.example.demo.dto.RoomDetails;
import com.example.demo.entity.Room;

import java.util.List;

public interface RoomService {

    List<RoomDetails> findAll();

    RoomDetails findById(Long id);

    Room save(Room room);

    void delete(Long id);
}
