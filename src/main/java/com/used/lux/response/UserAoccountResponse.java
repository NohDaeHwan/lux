package com.used.lux.response;

import com.used.lux.domain.constant.RoleType;
import com.used.lux.dto.UserAccountDto;

import java.time.LocalDateTime;

public record UserAoccountResponse(
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
    public static UserAoccountResponse of(Long id,
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
                                          String modifiedBy){
        return  new UserAoccountResponse(
                id,
                userEmail,
                userPassword,
                userName,
                phoneNumber,
                address,
                age,
                gender,
                nickName,
                memberGrade,
                reserveFund,
                role,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public  static  UserAoccountResponse from(UserAccountDto userAccountDto){
        return  new UserAoccountResponse(
                userAccountDto.id(), userAccountDto.userEmail(), userAccountDto.userPassword(),
                userAccountDto.userName(), userAccountDto.phoneNumber(), userAccountDto.address(),
                userAccountDto.age(), userAccountDto.gender(), userAccountDto.nickName(), userAccountDto.memberGrade(),
                userAccountDto.reserveFund(),userAccountDto.role(),userAccountDto.createdAt(),
                userAccountDto.createdBy(),userAccountDto.modifiedAt(), userAccountDto.modifiedBy()
        );

    }

}
