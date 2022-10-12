package com.used.lux.repository;

import com.used.lux.domain.UserAccountLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccountLogRepository extends JpaRepository<UserAccountLog, Long> {
    List<UserAccountLog> findByUserEmail(String userEmail);
}
