package com.used.lux.repository;

import com.used.lux.domain.UserWithdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWithDrawalRepository extends JpaRepository<UserWithdrawal,Long> {
}
