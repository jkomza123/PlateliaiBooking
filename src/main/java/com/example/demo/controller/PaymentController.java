package com.example.demo.controller;

import com.example.demo.service.BookingService;
import com.example.demo.service.EmailService;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.model.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks/stripe")
public class PaymentController {

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    private final BookingService bookingService;
    private final EmailService emailService;

    public PaymentController(
            BookingService bookingService,
            EmailService emailService
    ) {
        this.bookingService = bookingService;
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> handleStripeEvent(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader
    ) {
        Event event;

        try {
            event = Webhook.constructEvent(
                    payload,
                    sigHeader,
                    webhookSecret
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        }

        switch (event.getType()) {

            case "checkout.session.completed" -> {
                handleCheckoutCompleted(event);
            }

            case "checkout.session.expired" -> {
                handleCheckoutExpired(event);
            }
        }

        return ResponseEntity.ok("Received");
    }

    private void handleCheckoutCompleted(Event event) {
        Session session = (Session) event
                .getDataObjectDeserializer()
                .getObject()
                .orElseThrow();

        Long bookingId = Long.valueOf(
                session.getMetadata().get("booking_id")
        );

        bookingService.confirmBooking(bookingId);
        emailService.sendBookingEmail(bookingId);
    }

    private void handleCheckoutExpired(Event event) {
        Session session = (Session) event
                .getDataObjectDeserializer()
                .getObject()
                .orElseThrow();

        Long bookingId = Long.valueOf(
                session.getMetadata().get("booking_id")
        );

        bookingService.expireBooking(bookingId);
    }
}
