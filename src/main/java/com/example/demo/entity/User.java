package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
@Access(AccessType.FIELD)
@Entity
public class User  extends BaseEntity {
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String surname;
    @Column
    @NotBlank
    private String email;
    @Column
    private String oauthProvider;
    @Column
    private String oauthId;
    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Image avatar;
}