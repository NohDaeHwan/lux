package com.used.lux.domain.useraccount;

import javax.persistence.*;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.UserGrade;
import com.used.lux.domain.constant.RoleType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	private long userPoint;

	@Setter
	@Enumerated(EnumType.STRING)
	private RoleType role;

	@Setter
	@Column(length = 500)
	private String memo;
}
