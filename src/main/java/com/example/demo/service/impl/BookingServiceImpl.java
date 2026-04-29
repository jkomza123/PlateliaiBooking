package com.example.demo.service.impl;

import com.example.demo.dao.BookingDao;
import com.example.demo.dao.RoomDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.BookingDetails;
import com.example.demo.dto.StripeResponseDetails;
import com.example.demo.entity.Booking;
import com.example.demo.enums.BookingStatusEnum;
import com.example.demo.service.BookingService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.PriceService;
import com.example.demo.utils.BookingDtoUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;
    private final RoomDao roomDao;
    private final UserDao userDao;
    private final PriceService priceService;
    private final PaymentService paymentService;

    public BookingServiceImpl(BookingDao bookingDao, RoomDao roomDao, UserDao userDao, PriceService priceService, PaymentService paymentService) {
        this.bookingDao = bookingDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
        this.priceService = priceService;
        this.paymentService = paymentService;
    }

    public List<BookingDetails> findAll() {
        return bookingDao.findAll()
                .stream()
                .map(BookingDtoUtils::mapBookingToDetails)
                .collect(Collectors.toList());
    }

    public List<BookingDetails> findBookingsByRoom(Long id, String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        return bookingDao.findBookingsByRoom(id, fromDate, toDate, BookingStatusEnum.CANCELLED, BookingStatusEnum.EXPIRED)
                .stream()
                .map(BookingDtoUtils::mapBookingToMinimalDetails)
                .collect(Collectors.toList());
    }

    public BookingDetails findById(Long id) {
        return BookingDtoUtils.mapBookingToDetails(bookingDao.findById(id).orElse(null));
    }

    @Transactional
    public Booking save(BookingDetails bookingDetails) {
        var room = roomDao.findById(bookingDetails.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        var price = priceService.calculatePrice(room.getPrice(), bookingDetails.getDateFrom(), bookingDetails.getDateTo());

        Booking booking = new Booking();
        booking.setId(null);
        booking.setEmail(bookingDetails.getEmail());
        booking.setPrice(price);
        booking.setDateFrom(bookingDetails.getDateFrom());
        booking.setDateTo(bookingDetails.getDateTo());
        booking.setStatus(BookingStatusEnum.PENDING);
        booking.setRoom(room);
        booking.setPhoneNumber(bookingDetails.getPhoneNumber());
        booking.setOwnerName(bookingDetails.getOwnerName());
        booking.setLanguage(bookingDetails.getLanguage() != null ? bookingDetails.getLanguage() : "lt");
        return bookingDao.save(booking);
    }

    @Transactional
    public Booking saveBookingWithLock(BookingDetails bookingDetails) {
        var overlapping = bookingDao.findBookingsByRoom(
                bookingDetails.getRoomId(),
                bookingDetails.getDateFrom(),
                bookingDetails.getDateTo(),
                BookingStatusEnum.CANCELLED,
                BookingStatusEnum.EXPIRED
        );

        if (!overlapping.isEmpty()) throw new IllegalArgumentException("Booking on this date already exists");

        return save(bookingDetails);
    }

    public StripeResponseDetails createBookingAndCheckout(BookingDetails bookingDetails) {
        Booking booking = saveBookingWithLock(bookingDetails);

        long amountInCents = booking.getPrice()
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP)
                .longValueExact();

        String orderNumber = booking.getLanguage().equals("en") ? "Order #" + booking.getId() + " - " + booking.getRoom().getNameEn() : "Užsakymas #" + booking.getId() + " - " + booking.getRoom().getNameLt();

        return paymentService.checkoutBooking(
                booking.getId(),
                booking.getRoom().getId(),
                amountInCents,
                orderNumber
        );
    }

    @Transactional
    public void confirmBooking(Long bookingId) {
        Booking booking = bookingDao.findById(bookingId)
                .orElseThrow();

        String token = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        booking.setDeleteToken(token);
        booking.setStatus(BookingStatusEnum.CONFIRMED);
    }

    @Transactional
    public void expireBooking(Long bookingId) {
        Booking booking = bookingDao.findById(bookingId)
                .orElseThrow();
        if (booking.getStatus() == BookingStatusEnum.CONFIRMED) {
            throw new RuntimeException("Booking cannot be expired");
        }
        booking.setStatus(BookingStatusEnum.EXPIRED);
    }

    @Transactional
    public void cancelBooking(Long id, String token) {
        Booking booking = bookingDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (!Objects.equals(booking.getDeleteToken(), token)) {
            throw new RuntimeException("Invalid token");
        }
        if (booking.getStatus() != BookingStatusEnum.CONFIRMED) {
            throw new RuntimeException("Booking cannot be deleted");
        }
        booking.setStatus(BookingStatusEnum.CANCELLED);
    }
}
