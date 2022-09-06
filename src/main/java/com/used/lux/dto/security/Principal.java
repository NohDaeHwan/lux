package com.used.lux.dto.security;

import com.used.lux.domain.constant.RoleType;
import com.used.lux.dto.UserAccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record Principal(
        Long id,
        RoleType role,
        String userEmail,
        String userPassword
) implements UserDetails {

    public static Principal of(Long id, RoleType role, String userEmail, String userPassword) {
        return new Principal(
                id,
                role,
                userEmail,
                userPassword
        );
    }

    public static Principal from(UserAccountDto dto) {
        return Principal.of(
                dto.id(),
                dto.role(),
                dto.userEmail(),
                dto.userPassword()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                id,
                userEmail,
                userPassword,
                role
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleType> roleTypes = Set.of(role);

        return roleTypes.stream()
                .map(RoleType::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String getPassword() {
        return userEmail;
    }

    @Override
    public String getUsername() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
