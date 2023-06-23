package com.example.jwtsecurity.domain.dto.response;

import com.example.jwtsecurity.domain.dto.BoardDTO;
import com.example.jwtsecurity.domain.dto.MemberDTO;
import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardListResponse {
    private List<BoardEntity> boardDTOList;

    public static BoardListResponse toBoardListResponse(MemberEntity memberEntity) {
        BoardListResponse boardListResponse = new BoardListResponse();
        boardListResponse.setBoardDTOList(memberEntity.getBoardEntities());
        return boardListResponse;
    }
}

