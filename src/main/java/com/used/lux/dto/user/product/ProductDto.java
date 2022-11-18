package com.used.lux.dto.user.product;

import com.used.lux.domain.*;
import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.product.Product;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductDto(
        Long id,
        Long appraisalId,
        String productName,
        Long productBrandId,
        String productBrandName,
        String productGender,
        String productColor,
        String productSize,
        int appraisalPrice,
        String appraisalComment,
        String productGrade,
        Long categoryBId,
        String categoryBName,
        Long categoryMId,
        String categoryMName,
        State productState,
        int productPrice,
        String productSellType,
        String productContent,
        int productViewCount,
        Set<ImageDto> imageDtos,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ProductDto of(Long id, Long appraisalId, String productName, Long productBrandId, String brandName, String gender, String color, String size,         int appraisalPrice,
                                String appraisalComment,String grade,
                                Long categoryBId, String categoryBName, Long categoryMId, String categoryMName, State productState, int productPrice,
                                String productSellType, String productContent, int productViewCount, Set<ImageDto> imageDtos,
                                LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductDto(id, appraisalId, productName, productBrandId, brandName, gender, color, size, appraisalPrice,appraisalComment,grade, categoryBId, categoryBName,
                categoryMId, categoryMName, productState, productPrice, productSellType, productContent, productViewCount,
                imageDtos, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ProductDto from(Product entity) {
        return new ProductDto(
                entity.getId(),
                entity.getAppraisal().getId(),
                entity.getAppraisal().getAppraisalRequest().getAppraisalProductName(),
                entity.getAppraisal().getAppraisalRequest().getAppraisalBrand().getId(),
                entity.getAppraisal().getAppraisalRequest().getAppraisalBrand().getBrandName(),
                entity.getAppraisal().getAppraisalRequest().getAppraisalGender(),
                entity.getAppraisal().getAppraisalRequest().getAppraisalColor(),
                entity.getAppraisal().getAppraisalRequest().getAppraisalSize(),
                entity.getAppraisal().getAppraisalPrice(),
                entity.getAppraisal().getAppraisalComment(),
                entity.getAppraisal().getAppraisalGrade(),
                entity.getCategoryB().getId(),
                entity.getCategoryB().getCategoryBName(),
                entity.getCategoryM().getId(),
                entity.getCategoryM().getCategoryMName(),
                entity.getState(),
                entity.getProductPrice(),
                entity.getProductSellType(),
                entity.getProductContent(),
                entity.getProductViewCount(),
                entity.getImages().stream()
                        .map(ImageDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Product toEntity(Appraisal appraisal, CategoryB categoryB, CategoryM categoryM) {
        return Product.of(appraisal, categoryB, categoryM, productState, productPrice, productSellType, productContent,
                productViewCount);
    }

}
