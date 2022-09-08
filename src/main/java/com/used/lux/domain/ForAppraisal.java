package com.used.lux.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(name = "for_appraisal")
@Entity
public class ForAppraisal extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(name="product_name", nullable = false, length = 100)
	private String productName;

	@Setter
	@Column(name="brand_name", nullable = false, length = 100)
	private String brandName;

	@Setter
	@Column(nullable = false, length = 500)
	private String content;

	@Setter
	@Column(nullable = false, length = 100)
	private String lived;

	@Setter
	@Column(nullable = false, length = 100)
	private String purchase;

	@Setter
	@Column(name="purchase_price", nullable = false)
	private int purchasePrice;

	@Setter
	@Column(name="whether_apprisal", nullable = false)
	private boolean whetherApprisal;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@ToString.Exclude
	@OneToMany(mappedBy = "forAppraisal", cascade = CascadeType.ALL)
	private final Set<AppraisalImage> appraisalImage = new LinkedHashSet<>();

	protected ForAppraisal() {}

    private ForAppraisal(UserAccount userAccount, String productName, String brandName, String content,
                        String lived, String purchase, int purchasePrice) {
        this.userAccount = userAccount;
        this.productName = productName;
        this.brandName = brandName;
        this.content = content;
        this.lived = lived;
        this.purchase = purchase;
        this.purchasePrice = purchasePrice;
        this.whetherApprisal = false;
    }

	public static ForAppraisal of(UserAccount userAccount, String productName, String brandName, String content,
						 String lived, String purchase, int purchasePrice) {
		return new ForAppraisal(userAccount, productName, brandName, content, lived, purchase, purchasePrice);
	}

	// 감정에서 파일 처리 위함
    public void addImage(AppraisalImage appraisalImage) {
        this.appraisalImage.add(appraisalImage);

	// 감정에 파일이 저장되어있지 않은 경우
        if(appraisalImage.getForAppraisal() != this)
            // 파일 저장
        	appraisalImage.setForAppraisal(this);
    }

}
