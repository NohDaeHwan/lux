package com.used.lux.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(nullable = false, length = 100)
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
	@Column(length = 100)
	private String address;

	@Setter
	private int age;

	@Setter
	@Column(length = 100)
	private String gender;

	@Setter
	@Column(length = 100)
	private String nickName;

	@Setter
	@Column(nullable = false, length = 100)
	private String memberGrade;

	@Setter
	private int reserveFund;

	@Setter
	@Enumerated(EnumType.STRING)
	private RoleType role;

	protected UserAccount() {}

	private UserAccount(String userEmail, String userPassword, String userName, String phoneNumber, String address, int age,
						String gender, String nickName, String memberGrade, int reserveFund, RoleType role) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.age = age;
		this.gender = gender;
		this.nickName = nickName;
		this.memberGrade = memberGrade;
		this.reserveFund = reserveFund;
		this.role = role;
	}

	public static UserAccount of(String userEmail, String userPassword, String userName, String phoneNumber, String address, int age,
						String gender, String nickName, String memberGrade, int reserveFund, RoleType role) {
		return new UserAccount(userEmail, userPassword, userName, phoneNumber, address, age, gender, nickName, memberGrade, reserveFund, role);
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
