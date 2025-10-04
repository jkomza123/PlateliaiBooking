package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import com.example.demo.enums.ResourceStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "booking")
@Access(AccessType.FIELD)
@Entity
public class Booking extends BaseEntity {
    @Column
    private String email;
    @Column
    private String price;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;
    @Column
    private Timestamp startDate;
    @Column
    private Timestamp endDate;
    @Column
    private ResourceStatusEnum status;
}
