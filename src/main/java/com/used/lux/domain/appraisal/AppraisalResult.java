package com.used.lux.domain.appraisal;

import javax.persistence.*;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.constant.AppraisalGrade;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "appraisal_result")
@Entity
public class AppraisalResult extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 관리자 컬럼
	@Setter
	@Column(name="app_grade")
	@Enumerated(EnumType.STRING)
	private AppraisalGrade appGrade; // A, B, C, F

	@Setter
	@Column(name="app_comment", length = 100)
	private String appComment;

	@Setter
	@Column(name="app_price")
	private Long appPrice;
}
