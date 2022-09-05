package com.used.lux.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(name = "auction")
@Entity
public class Auction extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(nullable = false, length = 100)
	private String productName;

	@Setter
	@Column(nullable = false, length = 100)
	private String brandName;

	@Setter
	@Column(nullable = false, length = 100)
	private String bigCategory;

	@Setter
	@Column(nullable = false, length = 100)
	private String smallCategory;

	@Setter
	@Column(nullable = false)
	private int size;

	@Setter
	@Column(nullable = false, length = 100)
	private String gender;

	@Setter
	@Column(nullable = false, length = 100)
	private String state;

	@Setter
	@Column(nullable = false)
	private int price;

	@Setter
	@Column(nullable = false)
	private int startPrice;

	@Setter
	@Column(nullable = false)
	private int presentPrice;

	@Setter
	private int closingPrice;

	@Setter
	@Column(nullable = false)
	private LocalDateTime auctionStartDate;

	@Setter
	@Column(nullable = false)
	private LocalDateTime auctionClosingDate;

	@Setter
	private int viewCount;

	@Setter
	private int biddingCount;

	@Setter
	@Column(length = 100)
	private String bidder;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "img_url")
	private Image image;

	protected Auction() {}

	private Auction(String productName, String brandName, String bigCategory, String smallCategory, int size, String gender,
				   String state, int price, int startPrice, int presentPrice, int closingPrice, LocalDateTime auctionStartDate,
				   LocalDateTime auctionClosingDate, int viewCount, int biddingCount, String bidder, Image image) {
		this.productName = productName;
		this.brandName = brandName;
		this.bigCategory = bigCategory;
		this.smallCategory = smallCategory;
		this.size = size;
		this.gender = gender;
		this.state = state;
		this.price = price;
		this.startPrice = startPrice;
		this.presentPrice = presentPrice;
		this.closingPrice = closingPrice;
		this.auctionStartDate = auctionStartDate;
		this.auctionClosingDate = auctionClosingDate;
		this.viewCount = viewCount;
		this.biddingCount = biddingCount;
		this.bidder = bidder;
		this.image = image;
	}

	public static Auction of(String productName, String brandName, String bigCategory, String smallCategory, int size, String gender,
					String state, int price, int startPrice, int presentPrice, int closingPrice, LocalDateTime auctionStartDate,
					LocalDateTime auctionClosingDate, int viewCount, int biddingCount, String bidder, Image image) {
		return new Auction(productName, brandName, bigCategory, smallCategory, size, gender, state, price, startPrice,
				presentPrice, closingPrice, auctionStartDate, auctionClosingDate, viewCount, biddingCount, bidder, image);
	}

}
