package com.used.lux.domain.product;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.CategoryB;
import com.used.lux.domain.CategoryM;
import com.used.lux.domain.constant.ProductState;
import com.used.lux.domain.constant.SellType;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "product_log")
@Entity
public class ProductLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="prod_Id", nullable = false)
    private Long prodId;

    @Setter
    @Column(name="prod_nm", nullable = false, length = 100)
    private String prodNm;

    @Setter
    @Column(name = "prod_state_id")
    @Enumerated(EnumType.STRING)
    private ProductState prodState;

    @Setter
    @Column(name = "prod_brand")
    private String prodBrand;

    @Setter
    @Column(name = "cate_b_nm")
    private String cateBNm;

    @Setter
    @Column(name = "cate_m_nm")
    private String cateMNm;

    @Setter
    @Column(name = "prod_price", nullable = false)
    private long prodPrice;

    @Setter
    @Column(name = "prodsell_type")
    @Enumerated(EnumType.STRING)
    private SellType prodSellType;
}
