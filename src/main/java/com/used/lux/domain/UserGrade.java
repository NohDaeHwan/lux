package com.used.lux.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_grade")
@Entity
public class UserGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private int gradeStep;

    @Setter
    @Column(nullable = false)
    private String gradeName;

    @Setter
    @Column(nullable = false)
    private int discount;

    @Setter
    @Column(nullable = false)
    private int rankUp;
}
