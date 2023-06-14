package com.example.jwtsecurity.domain.dto;

import com.example.jwtsecurity.domain.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

    private Long id;
    private String name;
    private String password;
    private String newPassword;
    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setName(memberEntity.getMemberName());
        memberDTO.setPassword(memberEntity.getMemberPassword());
        return memberDTO;
    }
}

