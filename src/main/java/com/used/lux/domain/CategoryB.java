package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Table(name = "category_b")
@Entity
public class CategoryB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="category_b_name", nullable = false, length = 100)
    private String categoryBName;

    @ToString.Exclude
    @OneToMany(mappedBy = "categoryB", cascade = CascadeType.ALL)
    private final Set<CategoryM> categoryMs = new LinkedHashSet<>();

}
