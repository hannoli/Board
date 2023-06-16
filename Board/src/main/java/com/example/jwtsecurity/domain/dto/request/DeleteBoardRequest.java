package com.example.jwtsecurity.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@NoArgsConstructor()
public class DeleteBoardRequest {

    private String name= SecurityContextHolder.getContext().getAuthentication().getName();
    private Long boardId;



}
