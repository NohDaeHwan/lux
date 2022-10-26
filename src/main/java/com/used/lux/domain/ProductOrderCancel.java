package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@ToString(callSuper = true)
@Table(name = "product_order_cancel")
@Entity
public class ProductOrderCancel extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "order_cancel_id",nullable = false)
    private Long orderCancelId;

    @Setter
    @Column(length = 100)
    private String userName;

    @Setter
    @Column(name="product_name", nullable = false, length = 100)
    private String productName;

    @Setter
    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Setter
    @Column(name="cancel_term", length = 500)
    private String cancelTerm; // 취소사유

}
