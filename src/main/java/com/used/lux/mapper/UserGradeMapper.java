package com.used.lux.mapper;

import com.used.lux.domain.UserGrade;
import com.used.lux.dto.UserGradeDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserGradeMapper {
    UserGrade toEntity(UserGradeDto userGradeDto);

    UserGradeDto toDto(UserGrade userGrade);

    List<UserGradeDto> toDtoList(List<UserGrade> userGrade);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserGrade partialUpdate(UserGradeDto userGradeDto, @MappingTarget UserGrade userGrade);
}