package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Table(name = "user_account_log")
@Entity
public class UserAccountLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100, unique = true)
    private String userEmail;

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_grade")
    private UserGrade userGrade;

    @Setter
    private int point;

}
