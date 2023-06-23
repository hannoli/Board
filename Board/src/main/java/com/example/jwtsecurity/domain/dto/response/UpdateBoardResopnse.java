package com.example.jwtsecurity.domain.dto.response;


import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateBoardResopnse {

    private String newTexts;

    public static UpdateBoardResopnse toUpdateBoardResponse(BoardEntity boardEntity) {
        UpdateBoardResopnse  updateBoardResopnse = new UpdateBoardResopnse();
        updateBoardResopnse.setNewTexts(boardEntity.getBoardTexts());
return updateBoardResopnse;
    }
}
