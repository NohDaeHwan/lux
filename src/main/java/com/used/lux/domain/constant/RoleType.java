package com.used.lux.domain.constant;

import lombok.Getter;

public enum RoleType {

    USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    @Getter
    private final String name;

    RoleType(String name) {this.name = name;}

}
