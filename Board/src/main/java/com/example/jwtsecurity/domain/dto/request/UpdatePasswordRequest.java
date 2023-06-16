package com.example.jwtsecurity.domain.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@NoArgsConstructor()
public class UpdatePasswordRequest {
    private String nowPassword;
    private String newPassword;
    private String name = SecurityContextHolder.getContext().getAuthentication().getName();

}
