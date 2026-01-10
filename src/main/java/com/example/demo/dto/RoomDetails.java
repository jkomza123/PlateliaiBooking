package com.example.demo.dto;

import com.example.demo.entity.Feature;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class RoomDetails {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nameLt;
    @Getter
    @Setter
    private String nameEn;
    @Getter
    @Setter
    private String descriptionLt;
    @Getter
    @Setter
    private String descriptionEn;
    @Getter
    @Setter
    private List<String> photoUrlList;
    @Getter
    @Setter
    private BigDecimal price;
    @Getter
    @Setter
    private Integer peopleNumber;
    @Getter
    @Setter
    private List<Feature> features;
}
