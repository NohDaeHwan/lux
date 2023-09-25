package com.used.lux.mapper;

import com.used.lux.domain.product.Product;
import com.used.lux.dto.user.product.ImageDto;
import com.used.lux.dto.user.product.ProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    default ProductDto toDtoCustom(Product product) {
        List<ImageDto> imageList = product.getImages().stream()
                .map((item) -> {
                    return ImageDto.builder()
                            .productId(item.getId())
                            .origFileName(item.getOrigFileName())
                            .filePath(item.getFilePath())
                            .fileSize(item.getFileSize())
                            .createdAt(item.getCreatedAt())
                            .createdBy(item.getCreatedBy())
                            .modifiedAt(item.getModifiedAt())
                            .modifiedBy(item.getModifiedBy())
                            .build();
                }).toList();

        return ProductDto.builder()
                .id(product.getId())
                .prodNm(product.getProdNm())
                .prodBrand(product.getProdBrand().getBrandName())
                .prodGender(product.getProdGender().name())
                .prodColor(product.getProdColor())
                .prodSize(product.getProdSize())
                .prodGrade(product.getProdGrade().name())
                .prodState(product.getProdState().name())
                .prodPrice(product.getProdPrice())
                .prodContent(product.getProdContent())
                .prodViewCnt(product.getProdViewCnt())
                .images(imageList)
                .createdAt(product.getCreatedAt())
                .createdBy(product.getCreatedBy())
                .modifiedAt(product.getModifiedAt())
                .modifiedBy(product.getModifiedBy())
                .build();
    }

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}