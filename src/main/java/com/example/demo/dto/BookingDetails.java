package com.example.demo.dto;

import com.example.demo.enums.BookingStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDetails {
    @Getter
    @Setter
    private Long Id;
    @Getter
    @Setter
    private String ownerName;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String phoneNumber;
    @Getter
    @Setter
    private BigDecimal price;
    @Getter
    @Setter
    private Long roomId;
    @Getter
    @Setter
    private LocalDate dateFrom;
    @Getter
    @Setter
    private LocalDate dateTo;
    @Getter
    @Setter
    private BookingStatusEnum status;
}
