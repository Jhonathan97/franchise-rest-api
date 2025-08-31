package com.jhonathan.franchise_rest_api.util;

import java.util.Map;

public record ApiError(String message, Map<String, String> details) {
    public static ApiError of(String message) {
        return new ApiError(message, Map.of());
    }

    public static ApiError of(String message, Map<String, String> details) {
        return new ApiError(message, details);
    }
}