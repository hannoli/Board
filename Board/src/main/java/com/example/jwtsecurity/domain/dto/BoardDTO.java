package com.example.jwtsecurity.domain.dto;


import com.example.jwtsecurity.domain.entity.BoardEntity;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

   // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    private String title;
    private String texts;
    private String author = SecurityContextHolder.getContext().getAuthentication().getName();
    private LocalTime localTime = LocalTime.now();


    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(boardEntity.getBoardTitle());
        boardDTO.setTexts(boardEntity.getBoardTexts());
        boardDTO.setAuthor(boardEntity.getBoardAuthor());
        boardDTO.setLocalTime(boardEntity.getBoardLocaltime());
        return boardDTO;
    }
}
