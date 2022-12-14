package com.used.lux.domain.product;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.CategoryB;
import com.used.lux.domain.CategoryM;
import com.used.lux.domain.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Table(name = "product_log")
@Entity
public class ProductLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_b_id")
    private CategoryB categoryB;

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_m_id")
    private CategoryM categoryM;

    @Setter
    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Setter
    @Column(name = "product_sell_type", nullable = false, length = 100)
    private String productSellType;

    protected ProductLog() {}

    public ProductLog(Long id, Long productId, String productName, State productState, CategoryB categoryB, CategoryM categoryM, int productPrice, String productSellType) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productState = productState;
        this.categoryB = categoryB;
        this.categoryM = categoryM;
        this.productPrice = productPrice;
        this.productSellType = productSellType;
    }

    public static ProductLog of(Long id, Long productId, String productName, State productState, CategoryB categoryB, CategoryM categoryM, int productPrice, String productSellType) {
        return new ProductLog(id, productId, productName,productState, categoryB, categoryM, productPrice, productSellType);
    }

    public static ProductLog of(Long productId, String productName, State productState, CategoryB categoryB, CategoryM categoryM, int productPrice, String productSellType) {
        return new ProductLog(null, productId, productName,productState,categoryB,categoryM,productPrice, productSellType);
    }

}
