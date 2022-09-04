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
	@Column(nullable = false, length = 100)
	private String productName;

	@Setter
	@Column(nullable = false, length = 100)
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
	@Column(nullable = false)
	private int purchasePrice;

	@Setter
	@Column(nullable = false)
	private boolean whetherApprisal;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account__id")
	private UserAccount userAccount;

	@ToString.Exclude
	@OneToMany(mappedBy = "forAppraisal", cascade = CascadeType.ALL)
	private final Set<AppraisalImage> appraisalImage = new LinkedHashSet<>();

	@ToString.Exclude
	@OneToMany(mappedBy = "forAppraisal", cascade = CascadeType.ALL)
	private final Set<AppraisalComent> appraisalComents = new LinkedHashSet<>();
	
	@Builder
    public ForAppraisal(UserAccount userAccount, String productName, String brandName, String content,
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

	public ForAppraisal() {}

	// 감정에서 파일 처리 위함
    public void addImage(AppraisalImage appraisalImage) {
        this.appraisalImage.add(appraisalImage);

	// 감정에 파일이 저장되어있지 않은 경우
        if(appraisalImage.getForAppraisal() != this)
            // 파일 저장
        	appraisalImage.setForAppraisal(this);
    }

}
