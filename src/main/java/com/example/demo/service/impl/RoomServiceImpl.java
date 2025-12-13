package com.example.demo.service.impl;

import com.example.demo.dao.RoomDao;
import com.example.demo.dto.RoomDetails;
import com.example.demo.entity.Room;
import com.example.demo.service.RoomService;
import com.example.demo.utils.RoomDtoUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public List<RoomDetails> findAll() {
        return roomDao.findAll()
            .stream()
            .map(RoomDtoUtils::mapRoomToDetails)
            .collect(Collectors.toList());
    }

    public List<RoomDetails> searchRooms(String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        return roomDao.findAvailableRooms(fromDate, toDate)
            .stream()
            .map(RoomDtoUtils::mapRoomToDetails)
            .collect(Collectors.toList());
    }

    public RoomDetails findById(Long id) {
        return RoomDtoUtils.mapRoomToDetails(roomDao.findById(id).orElse(null));
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
