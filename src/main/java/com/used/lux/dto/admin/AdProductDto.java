package com.used.lux.dto.admin;

import com.used.lux.dto.*;

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
