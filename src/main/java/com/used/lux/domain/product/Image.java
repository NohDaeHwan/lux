package com.used.lux.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.used.lux.domain.AuditingFields;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "image")
@Entity
public class Image extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

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
