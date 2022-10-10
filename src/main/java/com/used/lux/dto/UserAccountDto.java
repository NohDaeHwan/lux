package com.used.lux.dto;

import com.used.lux.domain.UserAccount;
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
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static UserAccountDto of(Long id, String userEmail, String userPassword, String userName, String phoneNumber,
                                    int age, String gender, int userPoint, UserGrade userGrade, RoleType role)
    {
        return new UserAccountDto(id, userEmail, userPassword, userName, phoneNumber, age, gender,
                userPoint, userGrade, role, null, null, null, null);
    }

    public static UserAccountDto of(
            Long id, String userEmail, String userPassword, String userName, String phoneNumber,
            int age, String gender, int userPoint, UserGrade userGrade, RoleType role,
            LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy
    ) {
        return new UserAccountDto(id, userEmail, userPassword, userName, phoneNumber, age, gender, userPoint,
                userGrade, role, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUserEmail(),
                entity.getUserPassword(),
                entity.getUserName(),
                entity.getPhoneNumber(),
                entity.getAge(),
                entity.getGender(),
                entity.getPoint(),
                entity.getUserGrade(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(userEmail, userPassword, userName, phoneNumber, age, gender, userPoint, userGrade, role);
    }

}
