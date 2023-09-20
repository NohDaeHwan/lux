package com.used.lux.mapper;

import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.dto.user.appraisal.AppraisalImageDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppraisalMapper {

//    Appraisal toEntity(AppraisalDto appraisalDto);

    @Mappings({
            @Mapping(target = "appBrand", expression = "java(appraisal.getAppBrand().getBrandName())")
    })
    AppraisalDto toDto(Appraisal appraisal);

    default AppraisalDto toDtoCustom(Appraisal app, List<AppraisalImageDto> imagelist) {
        return AppraisalDto.builder()
                .id(app.getId())
                .appProdNm(app.getAppProdNm())
                .appBrand(app.getAppBrand().getBrandName())
                .appGender(app.getAppGender().name())
                .appColor(app.getAppColor())
                .appSize(app.getAppSize())
                .appState(app.getAppState().name())
                .userId(app.getUserAccount().getId())
                .userEmail(app.getUserAccount().getUserEmail())
                .userNm(app.getUserAccount().getUserName())
                .appResultId(app.getAppResultId())
                .imageList(imagelist)
                .createdAt(app.getCreatedAt())
                .createdBy(app.getCreatedBy())
                .modifiedAt(app.getModifiedAt())
                .modifiedBy(app.getModifiedBy())
                .build();
    }

    List<AppraisalDto> toDtoList(List<AppraisalDto> appraisalList);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Appraisal partialUpdate(AppraisalDto appraisalDto, @MappingTarget Appraisal appraisal);
}