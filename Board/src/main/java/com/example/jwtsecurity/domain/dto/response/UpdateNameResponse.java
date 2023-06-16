package com.example.jwtsecurity.domain.dto.response;

import com.example.jwtsecurity.domain.dto.MemberDTO;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateNameResponse {
    private String password;
    private String newName;

    public static UpdateNameResponse toUpdateNameResponse(MemberEntity memberEntity) {
        UpdateNameResponse updateNameResponse = new UpdateNameResponse();
        updateNameResponse.setNewName(memberEntity.getMemberName());
        updateNameResponse.setPassword(memberEntity.getMemberPassword());
        return updateNameResponse;
    }

}
