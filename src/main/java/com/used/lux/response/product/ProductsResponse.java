package com.used.lux.response.product;

import com.used.lux.domain.State;
import com.used.lux.dto.ImageDto;
import com.used.lux.dto.ProductDto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductsResponse(
        Long id,
        String productName,
        String productBrand,
        String productGrade,
        State productState,
        String productColor,
        int productPrice,
        String productSellType,
        int productViewCount,
        String imagePath,
        LocalDateTime createdAt,
        String createdBy
)  {

    public static ProductsResponse of(Long id, String productName, String productBrand, String productGrade, State productState, String productColor, int productPrice,
                            String productSellType, int productViewCount, String imagePath, LocalDateTime createdAt, String createdBy) {
        return new ProductsResponse(id, productName, productBrand, productGrade, productState, productColor, productPrice, productSellType,
                productViewCount, imagePath, createdAt, createdBy);
    }

    public static ProductsResponse from(ProductDto productDto) {
        List<ImageDto> image = productDto.imageDtos().stream().toList();
        return new ProductsResponse(
                productDto.id(),
                productDto.productName(),
                productDto.productBrandName(),
                productDto.productGrade(),
                productDto.productState(),
                productDto.productColor(),
                productDto.productPrice(),
                productDto.productSellType(),
                productDto.productViewCount(),
                image.get(0).filePath(),
                productDto.createdAt(),
                productDto.createdBy()
        );
    }

}
