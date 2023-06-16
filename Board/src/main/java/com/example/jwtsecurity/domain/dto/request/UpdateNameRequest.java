package com.example.jwtsecurity.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor()
public class UpdateNameRequest {
    private String nowName;
    private String newName;
    private String password;

}
