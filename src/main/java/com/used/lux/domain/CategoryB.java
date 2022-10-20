package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
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

    protected CategoryB() {}

    private CategoryB(Long id, String categoryBName) {
        this.id = id;
        this.categoryBName = categoryBName;
    }

    public static CategoryB of(Long id, String categoryBName) {
        return new CategoryB(id, categoryBName);
    }

    public static CategoryB of(String categoryBName) {
        return new CategoryB(null, categoryBName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryB that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
