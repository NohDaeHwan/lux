package com.used.lux.repository.useraccount;

import com.used.lux.domain.useraccount.UserAccountLog;
import com.used.lux.dto.TotalPointDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface UserAccountLogRepository extends JpaRepository<UserAccountLog, Long> {
    @Query(nativeQuery = true, value = "select * from user_account_log where user_id=:userId",
            countQuery = "select count(*) from user_account_log where user_id=:userId")
    Page<UserAccountLog> findByUserIdFront(Long userId, Pageable pageable);

    @Query(value = "SELECT new com.used.lux.dto.TotalPointDto(SUM(ur.point)) FROM UserAccountLog ur " +
            "WHERE ur.userId=:userId AND ur.usageDetail = '충전' AND ur.saleNumber = '-'")
    TotalPointDto getTotalPoint(Long userId);

    @Query(nativeQuery = true,value = "select count(*) from user_account_log where created_at >=:sD and created_at <= now() ;")
    Long countOrderByCreatedAt(@Param("sD") String sectionStartDate);

    List<UserAccountLog> findByUserId(Long userId);
}
