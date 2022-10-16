package com.used.lux.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(name = "appraisal")
@Entity
public class Appraisal extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(name="appraisal_product_name", nullable = false, length = 100)
	private String appraisalProductName;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appraisal_brand_id")
	private Brand appraisalBrand;

	@Setter
	@Column(name="appraisal_content", nullable = false, length = 500)
	private String appraisalContent;

	@Setter
	@Column(name="appraisal_gender", nullable = false, length = 100)
	private String appraisalGender;

	@Setter
	@Column(name="appraisal_color", nullable = false, length = 100)
	private String appraisalColor;

	@Setter
	@Column(name="appraisal_size", nullable = false, length = 100)
	private String appraisalSize;

	// 관리자 컬럼
	@Setter
	@Column(name="appraisal_grade", length = 100)
	private String appraisalGrade; // A, B, C, F

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appraisal_state")
	private State appraisalState;

	@Setter
	@Column(name="appraisal_comment", length = 100)
	private String appraisalComment;

	@Setter
	@Column(name="appraisal_price")
	private int appraisalPrice;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	protected Appraisal() {}

	private Appraisal(String appraisalProductName, Brand appraisalBrand, String appraisalContent,
					 String appraisalGender, String appraisalColor, String appraisalSize, String appraisalGrade,
					 State appraisalState, String appraisalComment, int appraisalPrice, UserAccount userAccount) {
		this.appraisalProductName = appraisalProductName;
		this.appraisalBrand = appraisalBrand;
		this.appraisalContent = appraisalContent;
		this.appraisalGender = appraisalGender;
		this.appraisalColor = appraisalColor;
		this.appraisalSize = appraisalSize;
		this.appraisalGrade = appraisalGrade;
		this.appraisalState = appraisalState;
		this.appraisalComment = appraisalComment;
		this.appraisalPrice = appraisalPrice;
		this.userAccount = userAccount;
	}

	public static Appraisal of(String appraisalProductName, Brand appraisalBrandName, String appraisalContent,
							   String appraisalGender, String appraisalColor, String appraisalSize, String appraisalGrade,
							   State appraisalState, String appraisalComment, int appraisalPrice,
							   UserAccount userAccount) {
		return new Appraisal(appraisalProductName, appraisalBrandName, appraisalContent, appraisalGender, appraisalColor,
				appraisalSize, appraisalGrade, appraisalState, appraisalComment, appraisalPrice, userAccount);
	}

}
