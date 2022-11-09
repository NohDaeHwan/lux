package com.used.lux.dto.security;

import com.used.lux.domain.UserGrade;
import com.used.lux.domain.constant.RoleType;
import com.used.lux.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record Principal(
        Long id,
        String userEmail,
        String userPassword,
        String userName,
        String phoneNumber,
        int age,
        String gender,
        int point,
        UserGrade userGrade,
        RoleType role,
        String memo
) implements UserDetails {

    public static Principal of(Long id, String userEmail, String userPassword, String userName, String phoneNumber,
                     int age, String gender, int point, UserGrade userGrade, RoleType role, String memo) {
        return new Principal(id, userEmail, userPassword, userName, phoneNumber, age, gender, point, userGrade, role, memo);
    }

    //dto -> principal
    public static Principal from(UserAccountDto dto) {
        return Principal.of(
                dto.id(),
                dto.userEmail(),
                dto.userPassword(),
                dto.userName(),
                dto.phoneNumber(),
                dto.age(),
                dto.gender(),
                dto.userPoint(),
                dto.userGrade(),
                dto.role(),
                dto.memo()
        );
    }
    //dto를 만들어주는거
    public UserAccountDto toDto() {
        return UserAccountDto.of(
                id,
                userEmail,
                userPassword,
                userName,
                phoneNumber,
                age,
                gender,
                point,
                userGrade,
                role,
                memo
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
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userEmail;
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
