package com.example.jwtsecurity.domain.dto.response;

import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import com.example.jwtsecurity.domain.entity.Star;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StarResponse {
    private Long memberId;
    private Long boardId ;

    public static StarResponse toStarResponse(Star star) {
StarResponse starResponse = new StarResponse();
starResponse.setBoardId(star.getBoard().getId());
starResponse.setMemberId(star.getMember().getId());
    return starResponse;
    }
}
