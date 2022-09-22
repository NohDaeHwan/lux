package com.used.lux.domain;

import javax.persistence.*;

import com.used.lux.domain.constant.RoleType;
import lombok.*;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(name = "user_account")
@Entity
public class UserAccount extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(nullable = false, length = 100, unique = true)
	private String userEmail;

	@Setter
	@Column(nullable = false)
	private String userPassword;

	@Setter
	@Column(length = 100)
	private String userName;

	@Setter
	@Column(length = 11)
	private String phoneNumber;

	@Setter
	private int age;

	@Setter
	@Column(length = 100)
	private String gender;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_grade")
	private UserGrade userGrade;

	@Setter
	private int point;

	@Setter
	@Enumerated(EnumType.STRING)
	private RoleType role;

	protected UserAccount() {}

	private UserAccount(String userEmail, String userPassword, String userName, String phoneNumber, int age,
						String gender, int point, UserGrade userGrade, RoleType role) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.gender = gender;
		this.point = point;
		this.userGrade = userGrade;
		this.role = role;
	}

	public static UserAccount of(String userEmail, String userPassword, String userName, String phoneNumber, int age,
						String gender, int point, UserGrade userGrade, RoleType role) {
		return new UserAccount(userEmail, userPassword, userName, phoneNumber, age, gender, point, userGrade, role);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserAccount that)) return false;
		return id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
