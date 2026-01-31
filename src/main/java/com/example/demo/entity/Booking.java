package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import com.example.demo.enums.BookingStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    @Column
    private String ownerName;
    @Column
    private String phoneNumber;
    @Column
    private LocalDate dateFrom;
    @Column
    private LocalDate dateTo;
    @Column
    private BookingStatusEnum status;
}
