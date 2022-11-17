package com.used.lux.domain.auction;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(name = "auction")
@Entity
@NoArgsConstructor
public class Auction extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_id")
	private State state;

	@Setter
	@Column(name="start_price", nullable = false)
	private int startPrice; // 시작가격

	@Setter
	@Column(name="present_price", nullable = false)
	private int presentPrice; // 현재 가격

	@Setter
	@Column(name="closing_price")
	private int closingPrice; // 낙찰 가격

	@Setter
	@Column(name="auction_start_date", nullable = false)
	private LocalDateTime auctionStartDate; // 경매 시작일

	@Setter
	@Column(name="auction_closing_date", nullable = false)
	private LocalDateTime auctionClosingDate; // 경매 종료일

	@Setter
	@Column(name="bidding_count")
	private int biddingCount; // 입찰수

	@Setter
	@Column(length = 100)
	private String bidder; // 입찰자



	private Auction(Product product, State state, int startPrice, int presentPrice, int closingPrice, LocalDateTime auctionStartDate,
					LocalDateTime auctionClosingDate, int biddingCount, String bidder) {
		this.product = product;
		this.state = state;
		this.startPrice = startPrice;
		this.presentPrice = presentPrice;
		this.closingPrice = closingPrice;
		this.auctionStartDate = auctionStartDate;
		this.auctionClosingDate = auctionClosingDate;
		this.biddingCount = biddingCount;
		this.bidder = bidder;
	}

	public static Auction of(Product product, State state, int startPrice, int presentPrice, int closingPrice, LocalDateTime auctionStartDate,
							 LocalDateTime auctionClosingDate, int biddingCount, String bidder) {
		return new Auction(product, state, startPrice, presentPrice, closingPrice, auctionStartDate, auctionClosingDate, biddingCount, bidder);
	}

}
