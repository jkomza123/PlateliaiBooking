package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RoomDetails {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private List<String> photoUrlList;
}
