package com.example.jwtsecurity.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@NoArgsConstructor()
public class BoardListRequest {
    private String name = SecurityContextHolder.getContext().getAuthentication().getName();

}
