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
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(name = "product")
@Entity
public class Product extends AuditingFields {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(name = "product_name", nullable = false, length = 100)
	private String productName;

	@Setter
	@Column(name = "brand_name", nullable = false, length = 100)
	private String brandName;

	@Setter
	@Column(name = "big_category", nullable = false, length = 100)
	private String bigCategory;

	@Setter
	@Column(name = "small_category", nullable = false, length = 100)
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
	@Column(name = "payment_comple")
	@ColumnDefault("0")
	private int paymentComple;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "img_url")
	private Image image;

	protected Product() {}

	private Product(String productName, String brandName, String bigCategory, String smallCategory, int size,
				   String gender, String state, int price, int paymentComple, Image image) {
		this.productName = productName;
		this.brandName = brandName;
		this.bigCategory = bigCategory;
		this.smallCategory = smallCategory;
		this.size = size;
		this.gender = gender;
		this.state = state;
		this.price = price;
		this.paymentComple = paymentComple;
		this.image = image;
	}

	public static Product of(String productName, String brandName, String bigCategory, String smallCategory, int size,
					String gender, String state, int price, int paymentComple, Image image) {
		return new Product(productName, brandName, bigCategory, smallCategory, size, gender, state, price, paymentComple, image);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product product)) return false;
		return id != null && id.equals(product.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
