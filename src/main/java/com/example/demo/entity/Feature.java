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
@Table(name = "feature")
@Access(AccessType.FIELD)
@Entity
public class Feature extends BaseEntity {
    @Column(unique = true)
    private String code;
    @Column
    private String icon;
}
