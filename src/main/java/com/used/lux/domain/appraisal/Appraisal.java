package com.used.lux.domain.appraisal;

import com.used.lux.domain.*;
import com.used.lux.domain.constant.AppraisalState;
import com.used.lux.domain.constant.GenterType;
import com.used.lux.domain.useraccount.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "appraisal")
@Entity
public class Appraisal extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(name="app_prod_nm", nullable = false, length = 100)
	private String appProdNm;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "app_brand_id")
	private Brand appBrand;

	@Setter
	@Column(name = "app_gender")
	@Enumerated(EnumType.STRING)
	private GenterType appGender;

	@Setter
	@Column(name="app_color", nullable = false, length = 100)
	private String appColor;

	@Setter
	@Column(name="app_size", nullable = false, length = 100)
	private String appSize;

	@Setter
	@Column(name = "app_state")
	@Enumerated(EnumType.STRING)
	private AppraisalState appState;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@Setter
	@JoinColumn(name = "app_request_id")
	private Long appResultId;

	@ToString.Exclude
	@OneToMany(mappedBy = "appraisal", cascade = CascadeType.ALL)
	private final List<AppraisalImage> images = new ArrayList<>();
}
