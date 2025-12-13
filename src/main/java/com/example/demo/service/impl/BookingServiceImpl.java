package com.example.demo.service.impl;

import com.example.demo.dao.BookingDao;
import com.example.demo.dao.RoomDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.BookingDetails;
import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;
    private final RoomDao roomDao;
    private final UserDao userDao;

    public BookingServiceImpl(BookingDao bookingDao, RoomDao roomDao, UserDao userDao) {
        this.bookingDao = bookingDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
    }

    public List<Booking> findAll() {
        return bookingDao.findAll();
    }

    public Booking findById(Long id) {
        return bookingDao.findById(id).orElse(null);
    }

    @Transactional
    public Booking save(BookingDetails bookingDetails) {
        Booking booking = new Booking();
        booking.setId(null);
        booking.setEmail(bookingDetails.getEmail());
        booking.setPrice(bookingDetails.getPrice());
        booking.setStartDate(bookingDetails.getStartDate());
        booking.setEndDate(bookingDetails.getEndDate());
        booking.setStatus(bookingDetails.getStatus());

        booking.setRoom(roomDao.getReferenceById(bookingDetails.getRoom().getId()));
        booking.setOwner(userDao.getReferenceById(bookingDetails.getOwnerId()));
        return bookingDao.save(booking);
    }

    @Transactional
    public void delete(Long id) {
        bookingDao.deleteById(id);
    }
}
