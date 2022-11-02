package com.used.lux.repository;

import com.used.lux.domain.UserAccountLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAccountLogRepository extends JpaRepository<UserAccountLog, Long> {
    @Query(nativeQuery = true,value ="select * from user_account_log where user_email=:userEmail",
            countQuery="select count(*) from user_account_log where user_email=:userEmail")
    Page<UserAccountLog> findByUserEmailFront(String userEmail, Pageable pageable);

    List<UserAccountLog> findByUserEmail(String userEmail);
}
