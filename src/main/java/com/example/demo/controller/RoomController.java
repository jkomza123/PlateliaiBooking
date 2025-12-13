package com.example.demo.controller;

import com.example.demo.dto.RoomDetails;
import com.example.demo.entity.Room;
import com.example.demo.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDetails> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/search")
    public List<RoomDetails> searchRooms(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return roomService.searchRooms(from, to);
    }

    @GetMapping("/{id}")
    public RoomDetails getRoom(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.save(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        room.setId(id);
        return roomService.save(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
    }
}
