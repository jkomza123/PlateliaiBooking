package com.example.demo.service.impl;

import com.example.demo.dao.RoomDao;
import com.example.demo.entity.Room;
import com.example.demo.service.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public List<Room> findAll() {
        return roomDao.findAll();
    }

    public Room findById(Long id) {
        return roomDao.findById(id).orElse(null);
    }

    @Transactional
    public Room save(Room room) {
        return roomDao.save(room);
    }

    @Transactional
    public void delete(Long id) {
        roomDao.deleteById(id);
    }
}
