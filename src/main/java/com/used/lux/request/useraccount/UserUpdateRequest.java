package com.used.lux.request.useraccount;

public record UserUpdateRequest(
        int userPoint
) {
    public static UserUpdateRequest of(int userPoint) {
        return new UserUpdateRequest(userPoint);
    }
}
