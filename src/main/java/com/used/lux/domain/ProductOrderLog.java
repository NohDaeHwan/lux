package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Table(name = "product_order_log")
@Entity
public class ProductOrderLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100, unique = true)
    private String userEmail;

    @Setter
    @Column(name="product_name", nullable = false, length = 100)
    private String productName;

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_state_id")
    private State productState;

    @Setter
    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Setter
    @Column(name = "product_sell_type", nullable = false, length = 100)
    private String productSellType;

}