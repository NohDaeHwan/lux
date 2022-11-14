package com.used.lux.dto.admin;

import com.used.lux.domain.Auction;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardResponseVO {
    private Long lResult;
    private List<Auction> auctions;
    private List<String> stResult;
}
