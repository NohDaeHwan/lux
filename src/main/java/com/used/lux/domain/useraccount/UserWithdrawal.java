package com.used.lux.domain.useraccount;

import com.used.lux.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Table(name = "user_withdrawal")
@Entity
public class UserWithdrawal extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100, unique = true)
    private String userEmail;

    @Setter
    @Column(length = 100)
    private String userName;

    @Setter
    @Column(length = 11)
    private String phoneNumber;

    @Setter
    @Column(length = 500)
    private String content;

}
