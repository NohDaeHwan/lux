package com.used.lux.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(name = "rental_history")
@Entity
public class RentalHistory extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(length = 100)
	private String damageDetail;

	@Setter
	@Column(length = 100)
	private String damageStatus;

	@Setter
	@Column(nullable = false)
	private Date rentalStart;

	@Setter
	@Column(nullable = false)
	private Date rentalEnd;

	@Setter
	@Column(nullable = false, length = 100)
	private String rentalStatus;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account__id")
	private UserAccount userAccount;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private RentalProduct rentalProduct;
	
}
