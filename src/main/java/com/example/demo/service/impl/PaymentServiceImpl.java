package com.example.demo.service.impl;

import com.example.demo.dto.StripeResponseDetails;
import com.example.demo.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.apiKey}")
    String apiKey;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }

    public StripeResponseDetails checkoutBooking(Long bookingId, Long roomId, long amountInCents, String orderNumber) {
        long expiresAt = Instant.now()
                .plus(30, ChronoUnit.MINUTES)
                .getEpochSecond();

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setExpiresAt(expiresAt)
                        .setSuccessUrl(frontendUrl + "/success?bookingId=" + bookingId)
                        .setCancelUrl(frontendUrl + "/room/" + roomId)
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("eur")
                                                        .setUnitAmount(amountInCents)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData
                                                                        .builder()
                                                                        .setName(orderNumber)
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .putMetadata("booking_id", bookingId.toString())
                        .build();

        try {
            Session session = Session.create(params);

            return StripeResponseDetails.builder()
                    .status("SUCCESS")
                    .message("Payment session created")
                    .sessionId(session.getId())
                    .sessionUrl(session.getUrl())
                    .build();

        } catch (StripeException e) {
            return StripeResponseDetails.builder()
                    .status("FAILURE")
                    .message(e.getMessage())
                    .build();
        }
    }
}

