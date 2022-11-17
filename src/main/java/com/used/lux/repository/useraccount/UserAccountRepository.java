package com.used.lux.repository.useraccount;

import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.repository.querydsl.UserAccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>, UserAccountRepositoryCustom {
    Optional<UserAccount> findByUserEmail(String username);

}
