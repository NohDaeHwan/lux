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
	@Column(nullable = false, length = 100, unique = true)
	private String userName;

	@Setter
	@Column(nullable = false, length = 13, unique = true)
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

	@Setter
	@Column(length = 500)
	private String memo;

	protected UserAccount() {}

	private UserAccount(Long id, String userEmail, String userPassword, String userName, String phoneNumber, int age,
						String gender, int point, UserGrade userGrade, RoleType role, String memo) {
		this.id = id;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.gender = gender;
		this.point = point;
		this.userGrade = userGrade;
		this.role = role;
		this.memo = memo;
	}

	public static UserAccount of(Long id, String userEmail, String userPassword, String userName, String phoneNumber, int age,
						String gender, int point, UserGrade userGrade, RoleType role, String memo) {
		return new UserAccount(id, userEmail, userPassword, userName, phoneNumber, age, gender, point, userGrade, role, memo);
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
