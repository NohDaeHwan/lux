package com.used.lux.domain.auction;

import com.used.lux.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Table(name = "auction_log")
@Entity
public class AuctionLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 100)
    private String bidder; // 입찰자

    @Setter
    @Column(name="product_Id", nullable = false)
    private Long productId;

    @Setter
    @Column(name="product_name", nullable = false, length = 100)
    private String productName; // 경매 제품 이름

    @Setter
    @Column(name="present_price", nullable = false)
    private int presentPrice; // 현재 가격

}
