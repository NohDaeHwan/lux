package com.used.lux.domain.appraisal;

import com.used.lux.domain.*;
import com.used.lux.domain.useraccount.UserAccount;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(name = "appraisal_request")
@Entity
public class AppraisalRequest extends AuditingFields {

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
	@Column(name="appraisal_gender", nullable = false, length = 100)
	private String appraisalGender;

	@Setter
	@Column(name="appraisal_color", nullable = false, length = 100)
	private String appraisalColor;

	@Setter
	@Column(name="appraisal_size", nullable = false, length = 100)
	private String appraisalSize;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appraisal_state_id")
	private State appraisalState;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@ToString.Exclude
	@OneToMany(mappedBy = "appraisalRequest", cascade = CascadeType.ALL)
	private final Set<AppraisalImage> images = new LinkedHashSet<>();

	protected AppraisalRequest() {}

	private AppraisalRequest(String appraisalProductName, Brand appraisalBrand, String appraisalGender, String appraisalColor,
                             String appraisalSize, State appraisalState, UserAccount userAccount) {
		this.appraisalProductName = appraisalProductName;
		this.appraisalBrand = appraisalBrand;
		this.appraisalGender = appraisalGender;
		this.appraisalColor = appraisalColor;
		this.appraisalSize = appraisalSize;
		this.appraisalState = appraisalState;
		this.userAccount = userAccount;
	}

	public static AppraisalRequest of(String appraisalProductName, Brand appraisalBrandName,
                                      String appraisalGender, String appraisalColor, String appraisalSize, State appraisalState,
									  UserAccount userAccount) {
		return new AppraisalRequest(appraisalProductName, appraisalBrandName, appraisalGender, appraisalColor,
				appraisalSize, appraisalState, userAccount);
	}

}
