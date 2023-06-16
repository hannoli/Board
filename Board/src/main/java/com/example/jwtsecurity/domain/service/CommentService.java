package com.example.jwtsecurity.domain.service;


import com.example.jwtsecurity.domain.dto.CommentDTO;
import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.CommentEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import com.example.jwtsecurity.domain.repository.BoardRepository;
import com.example.jwtsecurity.domain.repository.CommentRepository;
import com.example.jwtsecurity.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
private final BoardRepository boardRepository;
private final MemberRepository memberRepository;
    @Transactional
    public void commentRegister(CommentDTO commentDTO){
        BoardEntity board = boardRepository.findById(commentDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        MemberEntity member = memberRepository.findByMemberName(commentDTO.getCommentWriter())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        CommentEntity commentEntity = CommentEntity.builder()
                .commentWriter(commentDTO.getCommentWriter())
                .commentText(commentDTO.getCommentText())
                .boardEntity(board)
                .memberCommentEntities(member)
                .build();
        System.out.println("commentEntity = " + commentEntity);
        commentRepository.save(commentEntity);
    }
}
