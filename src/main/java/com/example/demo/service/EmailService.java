package com.example.demo.service;

public interface EmailService {
    void sendBookingEmail(Long bookingId);
    void sendBookingCancelledEmail(Long bookingId);
}
