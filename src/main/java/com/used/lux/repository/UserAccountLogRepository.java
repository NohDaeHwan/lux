package com.used.lux.repository;

import com.used.lux.domain.UserAccountLog;
import com.used.lux.dto.TotalPointDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAccountLogRepository extends JpaRepository<UserAccountLog, Long> {
    @Query(nativeQuery = true, value = "select * from user_account_log where user_email=:userEmail",
            countQuery = "select count(*) from user_account_log where user_email=:userEmail")
    Page<UserAccountLog> findByUserEmailFront(String userEmail, Pageable pageable);

    List<UserAccountLog> findByUserEmail(String userEmail);

    @Query(value = "select new com.used.lux.dto.TotalPointDto(SUM(ur.point)) from UserAccountLog ur where ur.userEmail=:userEmail AND ur.usageDetail='충전'")
    TotalPointDto getTotalPoint(String userEmail);

    @Query(nativeQuery = true,value = "select count(*) from user_account_log where created_at >=:sD and created_at <= now() ;")
    Long countOrderByCreatedAt(@Param("sD") String sectionStartDate);
}
