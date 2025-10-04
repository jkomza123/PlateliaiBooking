package com.example.demo.service.impl;

import com.example.demo.dao.BookingDao;
import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public List<Booking> findAll() {
        return bookingDao.findAll();
    }

    public Booking findById(Long id) {
        return bookingDao.findById(id).orElse(null);
    }

    @Transactional
    public Booking save(Booking booking) {
        return bookingDao.save(booking);
    }

    @Transactional
    public void delete(Long id) {
        bookingDao.deleteById(id);
    }
}
