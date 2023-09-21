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
    @JoinColumn(name = "cate_b_id")
    private CategoryB cateB;

    @Setter
    @Column(name="cate_m_nm", nullable = false, length = 100)
    private String cateMNm;

}
