package com.used.lux.domain.appraisal;

import com.used.lux.domain.AuditingFields;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "appraisal_image")
@Entity
public class AppraisalImage extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
    @JoinColumn(name = "appraisal_id")
    private Appraisal appraisal;

	@Setter
	@Column(name = "orig_file_name", nullable = false, length = 500)
	private String origFileName;

	@Setter
	@Column(name = "file_path", nullable = false, length = 500)
	private String filePath;

	@Setter
	@Column(name = "file_size", nullable = false)
	private Long fileSize;
}
