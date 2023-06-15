package com.example.jwtsecurity.domain.dto;

import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.CommentEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO {

    private String commentText;
    private String commentWriter= SecurityContextHolder.getContext().getAuthentication().getName();
    private Long boardId;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentText(commentEntity.getCommentText());
        commentDTO.setCommentWriter(commentDTO.getCommentWriter());
        commentDTO.setBoardId(commentDTO.getBoardId());
        return commentDTO;
    }
}
