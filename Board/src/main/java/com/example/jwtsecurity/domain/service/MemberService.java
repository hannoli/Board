package com.example.jwtsecurity.domain.service;

import com.example.jwtsecurity.domain.dto.BoardDTO;
import com.example.jwtsecurity.domain.dto.MemberDTO;
import com.example.jwtsecurity.domain.dto.request.UpdateNameRequest;
import com.example.jwtsecurity.domain.dto.response.UpdateNameResponse;
import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import com.example.jwtsecurity.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberDTO join(MemberDTO memberDTO) {
        Optional<MemberEntity> byName = memberRepository.findByMemberName(memberDTO.getName());
        if (byName.isEmpty() && Objects.equals(memberDTO.getPassword(), memberDTO.getNewPassword())) {
            MemberEntity newmemberEntity = MemberEntity.builder()
                    .memberName(memberDTO.getName())
                    .memberPassword(memberDTO.getPassword())
                    .build();
            memberRepository.save(newmemberEntity);
            MemberDTO m_dto = MemberDTO.toMemberDTO(newmemberEntity);
            return m_dto;
        } else {
            //이미 이름이 db에 있는 경우(메인화면으로 반환)
            return null;
        }
    }

    @Transactional
    public UpdateNameResponse updateName(UpdateNameRequest updateNameRequest) {
        Optional<MemberEntity> byName = memberRepository.findByMemberName(updateNameRequest.getNowName());
        if (byName.isPresent()) {
            if (Objects.equals(updateNameRequest.getPassword(), byName.get().getMemberPassword())) {
                MemberEntity memberEntity = byName.get();
                memberEntity.updateName(updateNameRequest.getNewName());

                UpdateNameResponse response = UpdateNameResponse.toUpdateNameResponse(memberEntity);
                return response;
            }
            return null;
        }
        return null;
    }



    @Transactional
    public boolean login(MemberDTO memberDTO) {
    /*1.이름으로 db에서 조회
    2.db에서  조회한 비번이랑 사용자가 입력한 비번이랑 일치하는지 확인
     */
        Optional<MemberEntity> byName = memberRepository.findByMemberName(memberDTO.getName());
        if (byName.isPresent()) {
            //조회 결과가 있다
            MemberEntity memberEntity = byName.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getPassword())) {
                System.out.println("equal");
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return true;
            } else {
                //불일치
                System.out.println("memberEntity.getMemberPassword() = " + memberEntity.getMemberPassword());
                System.out.println("memberDTO.getPassword() = " + memberDTO.getPassword());
                return false;
            }
        } else {
            return false;
        }

    }
}
