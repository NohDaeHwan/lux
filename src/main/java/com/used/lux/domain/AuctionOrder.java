package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@ToString(callSuper = true)
@Table(name = "auction_order")
@Entity
public class AuctionOrder extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(nullable = false, length = 100)
	private String name;

	@Setter
	@Column(nullable = false, length = 11)
	private String phoneNumber;

	@Setter
	@Column(nullable = false, length = 100)
	private String address;

	@Setter
	@Column(length = 100)
	private String email;

	@Setter
	@Column(length = 100)
	private String requestedTerm;

	@Setter
	private Long invoiceNumber;

	@Setter
	@Column(nullable = false, length = 100)
	private String paymentMethod;

	@Setter
	@Column(nullable = false)
	private int payment;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "auction_id")
	private Auction auction;
	
}
