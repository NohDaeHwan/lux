package com.used.lux.dto;

import java.util.Date;

public record ProductLogDto(
        Long id,
        Date create_at,
        String create_by,
        Date modified_at,
        String modified_by,
        String product_name,
        int product_price,
        String product_sell_type,
        Long category_b_id,
        Long category_m_id,
        Long product_state_id
) {
}
