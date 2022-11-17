package com.used.lux.dto.user.product;

import com.used.lux.domain.product.Image;
import com.used.lux.domain.product.Product;

import java.time.LocalDateTime;

public record ImageDto(
        Long productId,
        String origFileName,
        String filePath,
        Long fileSize,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ImageDto of(Long productId, String origFileName, String filePath, Long fileSize) {
        return new ImageDto(productId, origFileName, filePath, fileSize, null, null, null, null);
    }

    public static ImageDto from(Image entity) {
        return new ImageDto(
                entity.getProduct().getId(),
                entity.getOrigFileName(),
                entity.getFilePath(),
                entity.getFileSize(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Image toEntity(Product product) {
        return Image.of(product, origFileName, filePath, fileSize);
    }

}
