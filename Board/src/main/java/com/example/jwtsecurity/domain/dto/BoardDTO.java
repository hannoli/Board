package com.example.jwtsecurity.domain.dto;


import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.LikeEntity;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    private String title;
    private String texts;
    private Long memberId;
    private String author = SecurityContextHolder.getContext().getAuthentication().getName();
    private LocalTime localTime = LocalTime.now();
    private Long view;
    private Long like;


    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setView(boardEntity.getBoardView());
        boardDTO.setTitle(boardEntity.getBoardTitle());
        boardDTO.setTexts(boardEntity.getBoardTexts());
        boardDTO.setAuthor(boardEntity.getBoardAuthor());
        boardDTO.setLocalTime(boardEntity.getBoardLocaltime());
        boardDTO.setLike(boardEntity.getBoardLike());
        boardDTO.setMemberId(boardEntity.getMemberEntities().getId());
        return boardDTO;
    }


}
