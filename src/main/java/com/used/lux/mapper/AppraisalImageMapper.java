package com.used.lux.mapper;

import com.used.lux.domain.appraisal.AppraisalImage;
import com.used.lux.dto.user.appraisal.AppraisalImageDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppraisalImageMapper {
    AppraisalImage toEntity(AppraisalImageDto appraisalImageDto);

    AppraisalImageDto toDto(AppraisalImage appraisalImage);

    List<AppraisalImageDto> toDtoList(List<AppraisalImage> imageList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AppraisalImage partialUpdate(AppraisalImageDto appraisalImageDto, @MappingTarget AppraisalImage appraisalImage);
}