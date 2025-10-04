package com.example.demo.entity.base;

import com.example.demo.entity.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class File extends BaseEntity {
    private String url;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}