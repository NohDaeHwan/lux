package com.used.lux.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginMemberDto
{

    private String username;

    private String password;

}
