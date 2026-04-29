package com.example.demo.service;

import com.example.demo.dto.StripeResponseDetails;

public interface PaymentService {
    StripeResponseDetails checkoutBooking(Long bookingId, Long roomId, long amountInCents, String orderNumber);
}
