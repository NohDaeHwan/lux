package com.used.lux.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class JoinMemberDto
{
    @NotBlank(message = "nullTypeEmail")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "emailRegexpError")
    private String userName;

    @NotBlank(message = "nullTypePassword")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "passwordRegexpError")
    private String password;

    @NotBlank(message = "nullTypePasswordRepeat")
    private String passwordRepeat;

    @NotBlank(message = "nullTypeName")
    private String name;

    @NotBlank(message = "nullTypePhoneNumber")
    @Pattern(regexp = "^01(0|1|[6-9])-?([0-9]{3,4})-?([0-9]{4})$",message = "phoneRegexpError")
    private String phoneNumber;

    @NotBlank(message = "nullTypeAge")
    private String age;

    @NotBlank(message = "nullTypeGender")
    private String gender;

}
