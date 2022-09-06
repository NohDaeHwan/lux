package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@ToString(callSuper = true)
@Table(name = "appraisal_coment")
@Entity
public class AppraisalComent extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(nullable = false, length = 500)
	private String content;

	@Setter
	@Column(name="appraisal_img_url", nullable = false, length = 100)
	private String appraisalImgUrl;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "applicationed_id")
	private ForAppraisal forAppraisal;

}
