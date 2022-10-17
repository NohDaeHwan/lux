package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Table(name = "brand")
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="brand_name", nullable = false, length = 100)
    private String brandName;

    protected Brand() {}

    private Brand(Long id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public static Brand of(Long id, String brandName) {
        return new Brand(id, brandName);
    }

    public static Brand of(String brandName) {
        return new Brand(null, brandName);
    }
}
