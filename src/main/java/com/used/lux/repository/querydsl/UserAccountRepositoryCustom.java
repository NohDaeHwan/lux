package com.used.lux.repository.querydsl;

import com.used.lux.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAccountRepositoryCustom {
    Page<UserAccount> searchUser(String gender, String age, String grade, String date,
                                 String query, Pageable pageable);
}
