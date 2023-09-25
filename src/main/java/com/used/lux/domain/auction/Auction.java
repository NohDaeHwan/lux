package com.used.lux.domain.auction;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.used.lux.domain.*;
import com.used.lux.domain.constant.AuctionState;
import com.used.lux.domain.product.Product;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "auction")
@Entity
public class Auction extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(name = "auc_state")
	@Enumerated(EnumType.STRING)
	private AuctionState aucState;

	@Setter
	@Column(name="start_price", nullable = false)
	private long startPrice; // 시작가격

	@Setter
	@Column(name="present_price", nullable = false)
	private long presentPrice; // 현재 가격

	@Setter
	@Column(name="end_price")
	private long endPrice; // 낙찰 가격

	@Setter
	@Column(name="auc_start_date", nullable = false)
	private LocalDateTime aucStartDate; // 경매 시작일

	@Setter
	@Column(name="auc_end_date", nullable = false)
	private LocalDateTime aucEndDate; // 경매 종료일

	@Setter
	@Column(name="bidding_cnt")
	private int biddingCnt; // 입찰수

	@Setter
	@Column(length = 100)
	private String bidder; // 입찰자

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prod_id")
	private Product prod;
}
