package com.used.lux.domain.product;

import javax.persistence.*;

import com.used.lux.domain.*;
import com.used.lux.domain.constant.AppraisalGrade;
import com.used.lux.domain.constant.GenterType;
import com.used.lux.domain.constant.ProductState;
import com.used.lux.domain.constant.SellType;
import lombok.*;

import java.util.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "product")
@Entity
public class Product extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(name = "prod_nm", length = 100)
	private String prodNm;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cate_b_id")
	private CategoryB cateB;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cate_m_id")
	private CategoryM cateM;

	@Setter
	@Column(name = "prod_state")
	@Enumerated(EnumType.STRING)
	private ProductState prodState;

	@Setter
	@Column(name="prod_grade")
	@Enumerated(EnumType.STRING)
	private AppraisalGrade prodGrade; // S, A, B, F

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prod_brand")
	private Brand prodBrand;

	@Setter
	@Column(name = "prod_gender")
	@Enumerated(EnumType.STRING)
	private GenterType prodGender;

	@Setter
	@Column(name = "prod_size", length = 50)
	private String prodSize;

	@Setter
	@Column(name = "prod_color", length = 50)
	private String prodColor;

	@Setter
	@Column(name = "prod_price")
	private Long prodPrice;

	@Setter
	@Column(name = "prod_content", length = 1000)
	private String prodContent;

	@Setter
	@Column(name = "prod_view_cnt")
	private int prodViewCnt; // 조회수

	@Setter
	@Column(name = "prod_sell_type")
	@Enumerated(EnumType.STRING)
	private SellType prodSellType; // 판매 타입

	@ToString.Exclude
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private final List<Image> images = new ArrayList<>();
}
