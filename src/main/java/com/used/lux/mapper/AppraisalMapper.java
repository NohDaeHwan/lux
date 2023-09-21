package com.used.lux.mapper;

import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.appraisal.AppraisalResult;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.dto.user.appraisal.AppraisalImageDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppraisalMapper {

    default AppraisalDto toDtoCustom(Appraisal app, AppraisalResult appResult) {
        List<AppraisalImageDto> imageList = app.getImages().stream()
                .map((item) -> {
                    return AppraisalImageDto.builder()
                            .AppraisalId(item.getId())
                            .origFileName(item.getOrigFileName())
                            .filePath(item.getFilePath())
                            .fileSize(item.getFileSize())
                            .createdAt(item.getCreatedAt())
                            .createdBy(item.getCreatedBy())
                            .modifiedAt(item.getModifiedAt())
                            .modifiedBy(item.getModifiedBy())
                            .build();
                }).toList();
        AppraisalDto appraisalDto;
        if (appResult != null) {
            appraisalDto = AppraisalDto.builder().id(app.getId()).appProdNm(app.getAppProdNm()).appBrand(app.getAppBrand().getBrandName())
                    .appGender(app.getAppGender().name()).appColor(app.getAppColor()).appSize(app.getAppSize()).appState(app.getAppState().name())
                    .userId(app.getUserAccount().getId()).userEmail(app.getUserAccount().getUserEmail()).userNm(app.getUserAccount().getUserName())
                    .appResultId(app.getAppResultId()).appPrice(appResult.getAppPrice()).appGrade(appResult.getAppGrade().name()).appComment(appResult.getAppComment())
                    .imageList(imageList).createdAt(app.getCreatedAt()).createdBy(app.getCreatedBy()).modifiedAt(app.getModifiedAt()).modifiedBy(app.getModifiedBy())
                    .build();
        } else {
            appraisalDto = AppraisalDto.builder().id(app.getId()).appProdNm(app.getAppProdNm()).appBrand(app.getAppBrand().getBrandName())
                    .appGender(app.getAppGender().name()).appColor(app.getAppColor()).appSize(app.getAppSize()).appState(app.getAppState().name())
                    .userId(app.getUserAccount().getId()).userEmail(app.getUserAccount().getUserEmail()).userNm(app.getUserAccount().getUserName())
                    .appResultId(app.getAppResultId()).imageList(imageList).createdAt(app.getCreatedAt()).createdBy(app.getCreatedBy())
                    .modifiedAt(app.getModifiedAt()).modifiedBy(app.getModifiedBy()).build();
        }
        return appraisalDto;
    }

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Appraisal partialUpdate(AppraisalDto appraisalDto, @MappingTarget Appraisal appraisal);
}