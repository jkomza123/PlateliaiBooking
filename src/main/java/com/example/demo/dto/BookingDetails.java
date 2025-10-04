package com.example.demo.dto;

import com.example.demo.enums.ResourceStatusEnum;

import java.security.Timestamp;
import java.util.List;

public class BookingDetails {
    private String email;
    private String price;
    private List<RoomDetails> roomList;
    private String ownerName;
    private Timestamp startDate;
    private Timestamp endDate;
    private ResourceStatusEnum status;
}
