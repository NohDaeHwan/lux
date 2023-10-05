package com.used.lux.domain.auction;

import com.used.lux.domain.AuditingFields;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "auction_log")
@Entity
public class AuctionLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="user_id", nullable = false)
    private Long userId; // 입찰자

    @Setter
    @Column(name="auc_id", nullable = false)
    private Long aucId;

    @Setter
    @Column(name="prod_nm", nullable = false, length = 100)
    private String prodNm; // 경매 제품 이름

    @Setter
    @Column(name="present_price", nullable = false)
    private long presentPrice; // 현재 가격
}
