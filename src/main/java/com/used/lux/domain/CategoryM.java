package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Table(name = "category_m")
@Entity
public class CategoryM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_b_id")
    private CategoryB categoryB;

    @Setter
    @Column(name="category_m_name", nullable = false, length = 100)
    private String categoryMName;

}
