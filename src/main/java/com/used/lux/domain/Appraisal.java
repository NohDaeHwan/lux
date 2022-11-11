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

	// 관리자 컬럼
	@Setter
	@Column(name="appraisal_grade", length = 100)
	private String appraisalGrade; // A, B, C, F

	@Setter
	@Column(name="appraisal_comment", length = 100)
	private String appraisalComment;

	@Setter
	@Column(name="appraisal_price")
	private int appraisalPrice;

	@Setter
	@OneToOne(optional = false)
	@JoinColumn(name = "appraisal_request_id")
	private AppraisalRequest appraisalRequest;


	protected Appraisal() {}

	private Appraisal(String appraisalGrade, String appraisalComment, int appraisalPrice,AppraisalRequest appraisalRequest) {
		this.appraisalGrade = appraisalGrade;
		this.appraisalComment = appraisalComment;
		this.appraisalPrice = appraisalPrice;
		this.appraisalRequest = appraisalRequest;
	}

	public static Appraisal of(String appraisalGrade, String appraisalComment, int appraisalPrice,AppraisalRequest appraisalRequest) {
		return new Appraisal(appraisalGrade, appraisalComment, appraisalPrice, appraisalRequest);
	}

	public static Appraisal of(AppraisalRequest appraisalRequest) {
		return new Appraisal(null, null, 0, appraisalRequest);
	}

}
