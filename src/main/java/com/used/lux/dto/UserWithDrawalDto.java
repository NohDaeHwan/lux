package com.used.lux.dto;

import java.util.Date;

public record UserWithDrawalDto(
        Long id,
        Date create_at,
        String create_by,
        Date modified_at,
        String modified_by,
        String content,
        String phone_number,
        String user_email,
        String user_name
) {
}
