package com.example.demo.controller;

import com.example.demo.dto.BookingDetails;
import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @GetMapping("/room")
    public List<Booking> BookingsByRoom(
            @RequestParam Long id,
            @RequestParam String from,
            @RequestParam String to
    ) {
        return bookingService.findBookingsByRoom(id, from, to);
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingDetails booking) {
        return bookingService.save(booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
    }
}
