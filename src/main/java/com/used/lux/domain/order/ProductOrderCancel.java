package com.used.lux.domain.order;

import com.used.lux.domain.AuditingFields;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name="user_id", length = 100)
    private Long userId;

    @Setter
    @Column(name="cancel_term", length = 500)
    private String cancelTerm; // 취소사유
}
