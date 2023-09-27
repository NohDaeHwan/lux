package com.used.lux.domain.order;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.constant.ProductState;
import com.used.lux.domain.constant.SellType;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "product_order_log")
@Entity
public class ProductOrderLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Setter
    @Column(name="prod_Id", nullable = false)
    private Long prodId;

    @Setter
    @Column(name="prod_nm", nullable = false, length = 100)
    private String prodNm;

    @Setter
    @Column(name = "prod_state")
    @Enumerated(EnumType.STRING)
    private ProductState prodState;

    @Setter
    @Column(name = "order_price", nullable = false)
    private long orderPrice;

    @Setter
    @Column(name = "prod_sell_type")
    @Enumerated(EnumType.STRING)
    private SellType prodSellType;
}
