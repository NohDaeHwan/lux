package com.used.lux.dto;

import com.used.lux.domain.UserAccount;
import com.used.lux.domain.constant.RoleType;

import java.time.LocalDateTime;

public record UserAccountDto(
        Long id,
        String userEmail,
        String userPassword,
        String userName,
        String phoneNumber,
        String address,
        int age,
        String gender,
        String nickName,
        String memberGrade,
        int reserveFund,
        RoleType role,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static UserAccountDto of(Long id, String userEmail, String userPassword, String userName, String phoneNumber,
                                    String address, int age, String gender, String nickName, String memberGrade, int reserveFund, RoleType role)
    {
        return new UserAccountDto(id, userEmail, userPassword, userName, phoneNumber, address, age, gender,
                nickName, memberGrade, reserveFund, role, null, null, null, null);
    }

    public static UserAccountDto of(
            Long id, String userEmail, String userPassword, String userName, String phoneNumber, String address,
            int age, String gender, String nickName, String memberGrade, int reserveFund, RoleType role,
            LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy
    ) {
        return new UserAccountDto(id, userEmail, userPassword, userName, phoneNumber, address, age, gender, nickName, memberGrade,
                reserveFund, role, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUserEmail(),
                entity.getUserPassword(),
                entity.getUserName(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getAge(),
                entity.getGender(),
                entity.getNickName(),
                entity.getMemberGrade(),
                entity.getReserveFund(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(userEmail, userPassword, userName, phoneNumber, address, age, gender, nickName, memberGrade, reserveFund, role);
    }

}
