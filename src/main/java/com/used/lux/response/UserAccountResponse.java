package com.used.lux.response;

import com.used.lux.domain.UserGrade;
import com.used.lux.domain.constant.RoleType;
import com.used.lux.dto.UserAccountDto;

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

    public static UserAccountResponse of(Long id, String userEmail, String userName, String phoneNumber, int age,
                                         String gender, int userPoint, UserGrade userGrade, RoleType role,
                                         LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountResponse(id, userEmail, userName, phoneNumber, age, gender, userPoint, userGrade,
                role, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountResponse from(UserAccountDto userAccountDto){
        return new UserAccountResponse(
                userAccountDto.id(),
                userAccountDto.userEmail(),
                userAccountDto.userName(),
                userAccountDto.phoneNumber(),
                userAccountDto.age(),
                userAccountDto.gender(),
                userAccountDto.userPoint(),
                userAccountDto.userGrade(),
                userAccountDto.role(),
                userAccountDto.createdAt(),
                userAccountDto.createdBy(),
                userAccountDto.modifiedAt(),
                userAccountDto.modifiedBy()
        );
    }

}
