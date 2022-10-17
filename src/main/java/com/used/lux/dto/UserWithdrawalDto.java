package com.used.lux.dto;

import com.used.lux.domain.UserWithdrawal;

import java.time.LocalDateTime;

public record UserWithdrawalDto(
        Long id,
        String userEmail,
        String userName,
        String phoneNumber,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserWithdrawalDto of(Long id, String userEmail, String userName, String phoneNumber,
                             String content, LocalDateTime createdAt, String createdBy,
                             LocalDateTime modifiedAt, String modifiedBy) {
        return new UserWithdrawalDto(id, userEmail, userName, phoneNumber, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserWithdrawalDto from(UserWithdrawal entity) {
        return new UserWithdrawalDto(
                entity.getId(),
                entity.getUserEmail(),
                entity.getUserName(),
                entity.getPhoneNumber(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
