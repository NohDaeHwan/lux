package com.used.lux.domain.appraisal;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.LongAccumulator;

@Getter
@ToString(callSuper = true)
@Table(name = "appraisal_request_log")
@Entity
public class AppraisalRequestLog extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="appraisal_product_name",length = 100)
    private String appraisalProductName; // 검수 상품명

    @Setter
    @Column(name="appraisal_grade", length = 100)
    private String appraisalGrade; // 검수 등급

    @Setter
    @Column(name="appraisal_price")
    private int appraisalPrice; // 매입 가격


    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appraisal_state_id")
    private State appraisalState; // 검수 진행 상태

    @Setter
    @Column(name="user_id")
    private Long userId ; // 검수 진행 상태

    @Setter
    @Column(name="appraisal_id")
    private Long appraisalId ; // 검수 진행 상태

    protected AppraisalRequestLog() {}

    public AppraisalRequestLog(Long id, String appraisalProductName, String appraisalGrade, int appraisalPrice, State appraisalState, Long userId, Long appraisalId) {
        this.id = id;
        this.appraisalProductName = appraisalProductName;
        this.appraisalGrade = appraisalGrade;
        this.appraisalPrice = appraisalPrice;
        this.appraisalState = appraisalState;
        this.userId = userId;
        this.appraisalId = appraisalId;
    }


    public static AppraisalRequestLog of(Long id, String appraisalProductName, String appraisalGrade, int appraisalPrice, State appraisalState, Long userId, Long appraisalId) {
        return new AppraisalRequestLog(id, appraisalProductName, appraisalGrade, appraisalPrice,appraisalState,userId, appraisalId);
    }
    public static AppraisalRequestLog of( String appraisalProductName, String appraisalGrade, int appraisalPrice, State appraisalState, Long userId, Long appraisalId) {
        return new AppraisalRequestLog(null, appraisalProductName, appraisalGrade, appraisalPrice,appraisalState, userId, appraisalId);
    }


}

