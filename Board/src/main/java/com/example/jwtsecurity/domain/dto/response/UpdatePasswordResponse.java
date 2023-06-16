package com.example.jwtsecurity.domain.dto.response;


import com.example.jwtsecurity.domain.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdatePasswordResponse {
    public String name;
    public String updatePassword;

    public static UpdatePasswordResponse toupdatePasswordResponse(MemberEntity memberEntity) {
        UpdatePasswordResponse updateNameResponse = new UpdatePasswordResponse();
        updateNameResponse.name = memberEntity.getMemberName();
        updateNameResponse.updatePassword = memberEntity.getMemberPassword();
        return updateNameResponse;

    }
    }
