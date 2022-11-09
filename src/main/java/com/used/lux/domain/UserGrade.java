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

    protected UserGrade() {}

    private UserGrade(Long id, int gradeStep, String gradeName, int discount, int rankUp) {
        this.id = id;
        this.gradeStep = gradeStep;
        this.gradeName = gradeName;
        this.discount = discount;
        this.rankUp = rankUp;
    }

    public static UserGrade of(Long id, int gradeStep, String gradeName, int discount, int rankUp) {
        return new UserGrade(id, gradeStep, gradeName, discount, rankUp);
    }

    public static UserGrade of(int gradeStep, String gradeName, int discount, int rankUp) {
        return new UserGrade(null, gradeStep, gradeName, discount, rankUp);
    }

}
