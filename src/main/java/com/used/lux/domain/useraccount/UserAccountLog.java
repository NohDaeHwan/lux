package com.used.lux.domain.useraccount;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.UserGrade;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "user_account_log")
@Entity
public class UserAccountLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="user_id")
    private Long userId;

    @Setter
    @Column(name="order_id")
    private Long orderId; // 중고, 경매

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_grade")
    private UserGrade userGrade;

    @Setter
    private long point;

    @Setter
    @Column(name="usage_detail", nullable = false, length = 100)
    private String usageDetail;  // 세부사항

    @Setter
    @Column(name="sale_number", nullable = false, length = 100)
    private String saleNumber;   // 판매그룹/번호
}
