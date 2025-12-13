package com.example.demo.dto;

import com.example.demo.enums.ResourceStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class BookingDetails {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String price;
    @Getter
    @Setter
    private RoomDetails room;
    @Getter
    @Setter
    private String ownerName;
    @Getter
    @Setter
    private Long ownerId;
    @Getter
    @Setter
    private LocalDate startDate;
    @Getter
    @Setter
    private LocalDate endDate;
    @Getter
    @Setter
    private ResourceStatusEnum status;
}
