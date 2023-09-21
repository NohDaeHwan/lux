package com.used.lux.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category_b")
@Entity
public class CategoryB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="cate_b_nm", nullable = false, length = 100)
    private String cateBNm;


    @ToString.Exclude
    @OneToMany(mappedBy = "cateB", cascade = CascadeType.ALL)
    private final List<CategoryM> cateMList = new ArrayList<>();
}
