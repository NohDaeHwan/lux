package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(name = "product_order_cancel")
@Entity
public class ProductOrderCancel extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "order_id")
    private Long orderId;

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

    protected  ProductOrderCancel() {}

    private ProductOrderCancel(Long id, Long orderId, String userName, String productName, int productPrice, String cancelTerm) {
        this.id = id;
        this.orderId = orderId;
        this.userName = userName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.cancelTerm = cancelTerm;
    }

    public static ProductOrderCancel of(Long id, Long orderId, String userName, String productName,
                                        int productPrice, String cancelTerm) {
        return new ProductOrderCancel(id, orderId, userName, productName, productPrice, cancelTerm);
    }

    public static ProductOrderCancel of(Long orderId, String userName, String productName,
                                        int productPrice, String cancelTerm) {
        return new ProductOrderCancel(null, orderId, userName, productName, productPrice, cancelTerm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderCancel that = (ProductOrderCancel) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
