package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Table(name = "user_grade")
@Entity
public class UserGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String gradeName;

    @Setter
    @Column(nullable = false)
    private int discount;

}
