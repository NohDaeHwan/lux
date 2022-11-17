package com.used.lux.dto.admin;

import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.user.order.ProductOrderLogDto;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.dto.user.product.ProductLogDto;

import java.util.List;

public record AdProductDto(
        ProductDto productDto,
        List<ProductLogDto> productLogDtos,
        List<ProductOrderLogDto> ProductOrderLogDtos,
        List<AuctionLogDto> AuctionLogDtos
) {
    public static AdProductDto of(ProductDto productDto, List<ProductLogDto> productLogDtos,
                                  List<ProductOrderLogDto> ProductOrderLogDtos, List<AuctionLogDto> AuctionLogDtos) {
        return new AdProductDto(productDto, productLogDtos, ProductOrderLogDtos, AuctionLogDtos);
    }

}
