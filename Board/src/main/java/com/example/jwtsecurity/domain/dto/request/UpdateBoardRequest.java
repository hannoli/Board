package com.example.jwtsecurity.domain.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@NoArgsConstructor()
public class UpdateBoardRequest {

    private String name = SecurityContextHolder.getContext().getAuthentication().getName();
    private String newBoardTexts;
    private Long boardId;
}
