package com.used.lux.domain.order;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.State;
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
    @Column(name="product_Id", nullable = false)
    private Long productId;

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

    @Setter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    protected ProductOrderLog() {}
    private ProductOrderLog(Long id, String userEmail, Long productId, String productName, State productState, int productPrice, String productSellType, Long userId) {
        this.id = id;
        this.userEmail = userEmail;
        this.productId = productId;
        this.productName = productName;
        this.productState = productState;
        this.productPrice = productPrice;
        this.productSellType = productSellType;
        this.userId = userId;
    }

    public static ProductOrderLog of(Long id, String userEmail, Long productId, String productName, State productState, int productPrice, String productSellType, Long userId) {
        return new ProductOrderLog(id, userEmail, productId, productName,productState,productPrice, productSellType, userId);
    }

    public static ProductOrderLog of( String userEmail, Long productId, String productName, State productState, int productPrice, String productSellType, Long userId) {
        return new ProductOrderLog(null, userEmail, productId, productName,productState,productPrice, productSellType, userId);
    }


}
