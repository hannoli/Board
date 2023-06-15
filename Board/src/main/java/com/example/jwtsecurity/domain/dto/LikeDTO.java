package com.example.jwtsecurity.domain.dto;


import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.LikeEntity;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LikeDTO {

    private String likeUser = SecurityContextHolder.getContext().getAuthentication().getName();
    public static LikeDTO tolikeDTO(LikeEntity likeEntity) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setLikeUser(likeEntity.getLikeUsers());
        return likeDTO;
    }
}
