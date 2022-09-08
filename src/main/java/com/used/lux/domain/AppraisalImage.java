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
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@Setter
	@Column(name = "orig_file_name", nullable = false, length = 500)
	private String origFileName;

	@Setter
	@Column(name = "file_path", nullable = false, length = 500)
	private String filePath;

	@Setter
	@Column(name = "file_size", nullable = false)
	private Long fileSize;

	protected AppraisalImage() {}

    private AppraisalImage(ForAppraisal forAppraisal, UserAccount userAccount, String origFileName,
						  String filePath, Long fileSize){
		this.forAppraisal = forAppraisal;
		this.userAccount = userAccount;
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

	public static AppraisalImage of(ForAppraisal forAppraisal, UserAccount userAccount, String origFileName,
						   String filePath, Long fileSize){
		return new AppraisalImage(forAppraisal, userAccount, origFileName, filePath, fileSize);
	}

	public void setAppraisal(ForAppraisal forAppraisal){
        this.forAppraisal = forAppraisal;

	// 감정에 현재 파일이 존재하지 않는다면
        if(!forAppraisal.getAppraisalImage().contains(this))
            // 파일 추가
        	forAppraisal.getAppraisalImage().add(this);
    }

}
