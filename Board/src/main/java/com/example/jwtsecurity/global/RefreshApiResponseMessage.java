package com.example.jwtsecurity.global;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RefreshApiResponseMessage {
    private String status;
    private String message;
    private String refreshToken;
    public RefreshApiResponseMessage(Map<String, String> map) {
        this.status = map.get("status");
        this.message = map.get("message");
        this.refreshToken =map.get("refreshToken");
    }

    // Getters and setters
}
