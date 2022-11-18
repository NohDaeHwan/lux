package com.used.lux.request.useraccount;

public record UserNameUpdateRequest(
        String userName
) {
    public static UserNameUpdateRequest of(String userName) {
        return new UserNameUpdateRequest(userName);
    }
}
