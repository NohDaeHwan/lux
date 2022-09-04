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

}
