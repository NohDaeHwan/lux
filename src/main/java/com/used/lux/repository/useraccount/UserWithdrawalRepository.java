package com.used.lux.repository.useraccount;

import com.used.lux.domain.useraccount.UserWithdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWithdrawalRepository extends JpaRepository<UserWithdrawal,Long> {
}
