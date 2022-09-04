package com.used.lux.domain;

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
@Table(name = "rental_product")
@Entity
public class RentalProduct extends AuditingFields {

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
	private Integer size;

	@Setter
	@Column(nullable = false, length = 100)
	private String gender;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "img_url")
	private Image image;

	@Setter
	@Column(length = 100)
	private String rentalStatus;

	@Setter
	@Column(nullable = false)
	private Integer rentalPrice;
	
}
