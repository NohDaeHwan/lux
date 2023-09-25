package com.used.lux.mapper;

import com.used.lux.domain.useraccount.UserAccountLog;
import com.used.lux.dto.user.useraccount.UserAccountLogDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserAccountLogMapper {
    UserAccountLog toEntity(UserAccountLogDto userAccountLogDto);

    UserAccountLogDto toDto(UserAccountLog userAccountLog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserAccountLog partialUpdate(UserAccountLogDto userAccountLogDto, @MappingTarget UserAccountLog userAccountLog);
}