package com.used.lux.mapper;

import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserAccountMapper {
    UserAccount toEntity(UserAccountDto userAccountDto);

    UserAccountDto toDto(UserAccount userAccount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserAccount partialUpdate(UserAccountDto userAccountDto, @MappingTarget UserAccount userAccount);
}