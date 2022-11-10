package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Table(name = "appraisal_image")
@Entity
public class AppraisalImage extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
    @JoinColumn(name = "appraisal_request_id")
    private AppraisalRequest appraisalRequest;

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

    private AppraisalImage(AppraisalRequest appraisalRequest, String origFileName,
                           String filePath, Long fileSize){
		this.appraisalRequest = appraisalRequest;
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

	public static AppraisalImage of(AppraisalRequest appraisalRequest, String origFileName,
                                    String filePath, Long fileSize){
		return new AppraisalImage(appraisalRequest, origFileName, filePath, fileSize);
	}

	public void setAppraisal(AppraisalRequest appraisalRequest){
        this.appraisalRequest = appraisalRequest;

	// 감정에 현재 파일이 존재하지 않는다면
        if(!appraisalRequest.getImages().contains(this))
            // 파일 추가
			appraisalRequest.getImages().add(this);
    }

}
