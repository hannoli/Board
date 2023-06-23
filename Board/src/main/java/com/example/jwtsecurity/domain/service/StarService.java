package com.example.jwtsecurity.domain.service;

import com.example.jwtsecurity.domain.dto.BoardDTO;
import com.example.jwtsecurity.domain.dto.LikeDTO;
import com.example.jwtsecurity.domain.dto.response.StarResponse;
import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.LikeEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import com.example.jwtsecurity.domain.entity.Star;
import com.example.jwtsecurity.domain.repository.BoardRepository;
import com.example.jwtsecurity.domain.repository.MemberRepository;
import com.example.jwtsecurity.domain.repository.StarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StarService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final StarRepository starRepository;

    @Transactional
    public StarResponse getStar(Long boardId) {
        boolean alreadyStar = false;

        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberEntity memberEntity = memberRepository.findByMemberName(name)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        System.out.println("보드멤버" + memberEntity.getBoardMember());
        for (Star stars : memberEntity.getBoardMember()) {
            System.out.println("sstars = " + stars);
            Long starBoard = stars.getBoard().getId();
            System.out.println("이름 = " + memberEntity.getMemberName());
            System.out.println("name = " + name);
            if (Objects.equals(boardId, starBoard) && Objects.equals(memberEntity.getMemberName(), name)) {
                alreadyStar = true;
            }
        }
        if (!alreadyStar) {
            Star newStar = Star.builder()
                    .board(boardEntity)
                    .member(memberEntity)
                    .build();
            starRepository.save(newStar);
            StarResponse starResponse = StarResponse.toStarResponse(newStar);
            return starResponse;
        } else {
            starRepository.deleteByMemberAndBoard(memberEntity, boardEntity);
return null;
        }

    }

}
