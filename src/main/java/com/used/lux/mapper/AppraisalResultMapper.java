package com.used.lux.mapper;

import com.used.lux.domain.appraisal.AppraisalResult;
import com.used.lux.dto.user.appraisal.AppraisalResultDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppraisalResultMapper {
    AppraisalResult toEntity(AppraisalResultDto appraisalResultDto);

    AppraisalResultDto toDto(AppraisalResult appraisalResult);

    default AppraisalResultDto toDtoCustom(AppraisalResult appResult) {
        return AppraisalResultDto.builder()
                .id(appResult.getId())
                .appGrade(appResult.getAppGrade().name())
                .appComment(appResult.getAppComment())
                .appPrice(appResult.getAppPrice())
                .createdAt(appResult.getCreatedAt())
                .createdBy(appResult.getCreatedBy())
                .modifiedAt(appResult.getModifiedAt())
                .modifiedBy(appResult.getModifiedBy())
                .build();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AppraisalResult partialUpdate(AppraisalResultDto appraisalResultDto, @MappingTarget AppraisalResult appraisalResult);
}