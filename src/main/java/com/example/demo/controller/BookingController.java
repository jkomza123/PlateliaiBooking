package com.example.demo.controller;

import com.example.demo.dto.BookingDetails;
import com.example.demo.dto.StripeResponseDetails;
import com.example.demo.service.BookingService;
import com.example.demo.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final EmailService emailService;
    public BookingController(BookingService bookingService, EmailService emailService) {
        this.bookingService = bookingService;
        this.emailService = emailService;
    }

//    @GetMapping
//    public List<BookingDetails> getAllBookings() {
//        return bookingService.findAll();
//    }

    @GetMapping("/room")
    public List<BookingDetails> BookingsByRoom(
            @RequestParam Long id,
            @RequestParam String from,
            @RequestParam String to
    ) {
        return bookingService.findBookingsByRoom(id, from, to);
    }

    @GetMapping("/{id}")
    public BookingDetails getBooking(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @PostMapping("/checkout")
    public StripeResponseDetails createBooking(@RequestBody BookingDetails booking) {
        return bookingService.createBookingAndCheckout(booking);
    }

    @PostMapping("/cancel/{id}")
    public void deleteBooking(@PathVariable Long id, @RequestParam String token) {
        bookingService.cancelBooking(id, token);
        emailService.sendBookingCancelledEmail(id);
    }
}
