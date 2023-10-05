package com.used.lux.response.useraccount;

import com.used.lux.domain.UserGrade;
import com.used.lux.domain.constant.RoleType;

import java.time.LocalDateTime;

public record UserAccountResponse(
        Long id,
        String userEmail,
        String userName,
        String phoneNumber,
        int age,
        String gender,
        int userPoint,
        UserGrade userGrade,
        RoleType role,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
