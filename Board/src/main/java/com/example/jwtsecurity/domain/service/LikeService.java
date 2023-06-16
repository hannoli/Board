package com.example.jwtsecurity.domain.service;


import com.example.jwtsecurity.domain.dto.BoardDTO;
import com.example.jwtsecurity.domain.dto.LikeDTO;
import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.LikeEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import com.example.jwtsecurity.domain.repository.BoardRepository;
import com.example.jwtsecurity.domain.repository.LikeRepository;
import com.example.jwtsecurity.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
private final MemberRepository memberRepository;
    @Transactional
    public BoardDTO getLike(Long boardId) {
        LikeDTO likeDTO =new LikeDTO();
        boolean alreadyLiked = false;
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        MemberEntity member = memberRepository.findByMemberName(boardEntity.getBoardAuthor())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        System.out.println("likeDTO.getLikeUser() = " + likeDTO.getLikeUser());
        System.out.println("boardEntity.getLikeUserNames() = " + boardEntity.getLikeUserNames());
        for (String name : boardEntity.getLikeUserNames()) {
            System.out.println("name = " + name);

            if (Objects.equals(name, likeDTO.getLikeUser())) {
                alreadyLiked = true;
            }
        }
        if (!alreadyLiked) {
            boardEntity.increaseLike();
            LikeEntity newlikeEntity = LikeEntity.builder()
                    .likeUsers(likeDTO.getLikeUser())
                    .boardLikeEntity(boardEntity)
                    .memberLikeEntities(member)
                    .build();
            likeRepository.save(newlikeEntity);
            //String likeUser = boardEntity.getMemberEntities().getMemberName();
        } else {
            boardEntity.decreaseLike();
            likeRepository.deleteByLikeUsers(likeDTO.getLikeUser());
        }
        boardRepository.save(boardEntity);
        BoardDTO b_dto = BoardDTO.toBoardDTO(boardEntity);
        return b_dto;
    }

}
