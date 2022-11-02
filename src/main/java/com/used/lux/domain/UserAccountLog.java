package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(name = "user_account_log")
@Entity
public class UserAccountLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="user_email", nullable = false, length = 100, unique = true)
    private String userEmail;

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_grade")
    private UserGrade userGrade;

    @Setter
    private int point;

    @Setter
    @Column(name="usage_detail", nullable = false, length = 100)
    private String usageDetail;  // 세부사항

    @Setter
    @Column(name="sale_number", nullable = false, length = 100)
    private String saleNumber;   // 판매그룹/번호

    protected UserAccountLog() {}

    private UserAccountLog(Long id, String userEmail, UserGrade userGrade, int point,
                           String usageDetail, String saleNumber) {
        this.id = id;
        this.userEmail = userEmail;
        this.userGrade = userGrade;
        this.point = point;
        this.usageDetail = usageDetail;
        this.saleNumber = saleNumber;
    }

    public static UserAccountLog of(Long id, String userEmail, UserGrade userGrade, int point,
                                    String usageDetail, String saleNumber) {
        return new UserAccountLog(id, userEmail, userGrade, point, usageDetail, saleNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccountLog that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
