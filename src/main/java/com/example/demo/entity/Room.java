package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "room")
@Access(AccessType.FIELD)
@Entity
public class Room extends BaseEntity {
    @Column
    private String nameLt;
    @Column
    private String nameEn;
    @Column
    private String descriptionLt;
    @Column
    private String descriptionEn;
    @Column
    private String descriptionLtShort;
    @Column
    private String descriptionEnShort;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> photos;
    @Column
    private BigDecimal price;
    @Column
    private Integer peopleNumber;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "room_feature_map",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private List<Feature> features;
}
