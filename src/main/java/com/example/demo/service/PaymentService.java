package com.example.demo.service;

import com.example.demo.dto.StripeResponseDetails;

public interface PaymentService {
    StripeResponseDetails checkoutBooking(Long bookingId, long amountInCents, String orderNumber);
}
