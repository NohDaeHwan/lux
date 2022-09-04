package com.used.lux.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

import java.util.Objects;

@Getter
@ToString()
@Table(name = "image")
@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(nullable = true, length = 100)
	private String img1;

	@Setter
	@Column(nullable = true, length = 100)
	private String img2;

	@Setter
	@Column(nullable = true, length = 100)
	private String img3;

	protected Image() {}

	private Image(String img1, String img2, String img3) {
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
	}

	public static Image of(String img1, String img2, String img3) {
		return new Image(img1, img2, img3);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Image image)) return false;
		return id != null && id.equals(image.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
