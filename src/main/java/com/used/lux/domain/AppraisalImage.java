package com.used.lux.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(name = "appraisal_image")
@Entity
public class AppraisalImage extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@ManyToOne(optional = false)
    @JoinColumn(name = "appraisal_id")
    private ForAppraisal forAppraisal;

	@Setter
	@Column(nullable = false, length = 500)
	private String origFileName;

	@Setter
	@Column(nullable = false, length = 100)
	private String filePath;

	@Setter
	@Column(nullable = false)
	private Long fileSize;

    public AppraisalImage(String origFileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

	public AppraisalImage() {}

	public void setAppraisal(ForAppraisal forAppraisal){
        this.forAppraisal = forAppraisal;

	// 감정에 현재 파일이 존재하지 않는다면
        if(!forAppraisal.getAppraisalImage().contains(this))
            // 파일 추가
        	forAppraisal.getAppraisalImage().add(this);
    }

}
