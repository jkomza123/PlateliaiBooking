package com.example.demo.service.impl;

import com.example.demo.dao.BookingDao;
import com.example.demo.entity.Booking;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final BookingDao bookingDao;
    private final JavaMailSender mailSender;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    public EmailServiceImpl(
            BookingDao bookingDao,
            JavaMailSender mailSender
    ) {
        this.bookingDao = bookingDao;
        this.mailSender = mailSender;
    }

    public void sendBookingEmail(Long bookingId) {
        Booking booking = bookingDao.findById(bookingId)
                .orElseThrow();

        String link = frontendUrl + "/cancel-booking/" + booking.getId() + "/" + booking.getDeleteToken();

        String language = booking.getLanguage();

        String subject = "Užsakymas patvirtintas";
        String body = "Jūsų užsakymas patvirtintas.\n"
                + "Užsakymas #" + booking.getId() + " - " + booking.getRoom().getNameLt() + "\n"
                + "Gero poilsio :) \n\n"
                + "Jei norėsite užsakymą atšaukti:\n"
                + link;

        if (language.equals("en")) {
            subject = "Booking confirmed";
            body = "Your booking is confirmed.\n"
                    + "Order #" + booking.getId() + " - " + booking.getRoom().getNameEn() + "\n"
                    + "Have a nice stay :) \n\n"
                    + "If you wish to cancel the booking, click here:\n"
                    + link;
        }

        sendEmail(booking.getEmail(), subject, body);
    }

    public void sendBookingCancelledEmail(Long bookingId) {
        Booking booking = bookingDao.findById(bookingId)
                .orElseThrow();

        String link = frontendUrl + "/rent-rules";

        String language = booking.getLanguage();

        String subject = "Užsakymas atšauktas";
        String body = "Jūsų užsakymas atšauktas.\n"
                + "Užsakymas #" + booking.getId() + " - " + booking.getRoom().getNameLt() + "\n"
                + "Pinigai bus grąžinti į jūsų sąskaitą per 2d.d. vadovaujantis užsakymo taisyklėmis:\n"
                + link;

        if (language.equals("en")) {
            subject = "Booking cancelled";
            body = "Your booking is cancelled.\n"
                    + "Order #" + booking.getId() + " - " + booking.getRoom().getNameEn() + "\n"
                    + "Money will be transfered to your account in 2 work days according to our rental rules:\n"
                    + link;
        }

        sendEmail(booking.getEmail(), subject, body);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
