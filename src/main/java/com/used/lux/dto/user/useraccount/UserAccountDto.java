package com.used.lux.dto.user.useraccount;

import com.used.lux.domain.UserGrade;
import com.used.lux.domain.constant.RoleType;

import java.time.LocalDateTime;

public record UserAccountDto(
        Long id,
        String userEmail,
        String userPassword,
        String userName,
        String phoneNumber,
        int age,
        String gender,
        int userPoint,
        UserGrade userGrade,
        RoleType role,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
