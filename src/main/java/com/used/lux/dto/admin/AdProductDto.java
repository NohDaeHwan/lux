package com.used.lux.dto.admin;

import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.user.order.ProductOrderLogDto;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.dto.user.product.ProductLogDto;

import java.util.List;

public record AdProductDto(
        ProductDto prodList,
        List<ProductLogDto> prodLogList,
        List<ProductOrderLogDto> prodOrderLogList,
        List<AuctionLogDto> aucLogList
) {
    public static AdProductDto of(ProductDto prodList, List<ProductLogDto> prodLogList,
                                  List<ProductOrderLogDto> prodOrderLogList, List<AuctionLogDto> aucLogList) {
        return new AdProductDto(prodList, prodLogList, prodOrderLogList, aucLogList);
    }

}
