package com.used.lux.request;

public record UserMemoUpdateRequest(
        String memo
) {
    public static UserMemoUpdateRequest of(String memo) {
        return new UserMemoUpdateRequest(memo);
    }
}
