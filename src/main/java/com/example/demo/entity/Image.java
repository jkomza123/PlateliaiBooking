package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "image")
@Access(AccessType.FIELD)
@Entity
public class Image extends BaseEntity {
    private String url;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}