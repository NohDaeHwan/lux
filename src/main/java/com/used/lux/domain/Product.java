package com.used.lux.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(name = "product")
@Entity
public class Product extends AuditingFields {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@OneToOne(optional = false)
	@JoinColumn(name = "appraisal_id")
	private Appraisal appraisal;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_b_id")
	private CategoryB categoryB;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_m_id")
	private CategoryM categoryM;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_id")
	private State state;

	@Setter
	@Column(name = "product_price")
	private int productPrice;

	@Setter
	@Column(name = "product_sell_type", length = 100)
	private String productSellType; // 중고, 경매

	@Setter
	@Column(name = "product_content", length = 1000)
	private String productContent;

	@Setter
	@Column(name="product_view_count")
	private int productViewCount; // 조회수

	@ToString.Exclude
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private final Set<Image> images = new LinkedHashSet<>();

	protected Product() {}

	private Product(Appraisal appraisal, CategoryB categoryB, CategoryM categoryM, State state, int productPrice, String productSellType,
					String productContent, int productViewCount) {
		this.appraisal = appraisal;
		this.categoryB = categoryB;
		this.categoryM = categoryM;
		this.state = state;
		this.productPrice = productPrice;
		this.productSellType = productSellType;
		this.productContent = productContent;
		this.productViewCount = productViewCount;
	}

	//생성자
	public static Product of(Appraisal appraisal, CategoryB categoryB, CategoryM categoryM, State state, int productPrice, String productSellType,
							 String productContent, int productViewCount) {
		return new Product(appraisal, categoryB, categoryM, state, productPrice, productSellType, productContent, productViewCount);
	}

	//영속성
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
