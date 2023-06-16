package com.example.jwtsecurity.domain.service;

import com.example.jwtsecurity.domain.dto.BoardDTO;
import com.example.jwtsecurity.domain.dto.LikeDTO;
import com.example.jwtsecurity.domain.dto.request.DeleteBoardRequest;
import com.example.jwtsecurity.domain.dto.request.UpdateBoardRequest;
import com.example.jwtsecurity.domain.dto.response.UpdateBoardResopnse;
import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.CommentEntity;
import com.example.jwtsecurity.domain.entity.LikeEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import com.example.jwtsecurity.domain.repository.BoardRepository;
import com.example.jwtsecurity.domain.repository.CommentRepository;
import com.example.jwtsecurity.domain.repository.LikeRepository;
import com.example.jwtsecurity.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public void findMemberByName(String memberName) {
        Optional<MemberEntity> member = memberRepository.findByMemberName(memberName);
        member.ifPresentOrElse(
                m -> System.out.println("Member found: " + m.getId()),
                () -> System.out.println("Member not found.")
        );
    }

    @Transactional
    public BoardDTO register(BoardDTO boardDTO) {
        MemberEntity member = memberRepository.findByMemberName(boardDTO.getAuthor())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
/*        MemberEntity member = memberRepository.findByMemberName(boardDTO.getAuthor());
        member.orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));*/
        BoardEntity newBoardEntity = BoardEntity.builder()
                .boardAuthor((boardDTO.getAuthor()))
                .boardLocaltime(boardDTO.getLocalTime())
                .boardTitle(boardDTO.getTitle())
                .boardTexts(boardDTO.getTexts())
                .boardView(0L)
                .boardLike(0L)
                .memberEntity(member)
                .build();
        System.out.println("boardDTO.getAuthor() = " + boardDTO.getAuthor());
        boardRepository.save(newBoardEntity);
        boardDTO = BoardDTO.toBoardDTO(newBoardEntity);
        System.out.println("반환됭 보드 = " + boardDTO);
        return boardDTO;
    }

    @Transactional
    public void deleteBoard(DeleteBoardRequest deleteBoardRequest) {
        BoardEntity boardEntity = boardRepository.findById(deleteBoardRequest.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다"));
        if (Objects.equals(boardEntity.getBoardAuthor(), deleteBoardRequest.getName())) {
            List<CommentEntity> comments = boardEntity.getCommentEntities();
            List<LikeEntity> likes = boardEntity.getLikeEntities();
            commentRepository.deleteAll(comments);

            likeRepository.deleteAll(likes);
            boardRepository.delete(boardEntity);
        } else {
            throw new IllegalArgumentException("권한이 없습니다");
        }
    }

    @Transactional
    public BoardDTO getBoard(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        boardEntity.increaseView();
        System.out.println("getBoard() = " + boardEntity);
        BoardDTO b_dto = BoardDTO.toBoardDTO(boardEntity);
        System.out.println("b_dto = " + b_dto);
        return b_dto;
    }

    @Transactional
    public UpdateBoardResopnse boardUpdate(UpdateBoardRequest updateBoardRequest) {
        BoardEntity boardEntity = boardRepository.findById(updateBoardRequest.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("보드를 찾을 수 없습니다."));
        if (Objects.equals(boardEntity.getBoardAuthor(), updateBoardRequest.getName())) {
            boardEntity.updateTexts(updateBoardRequest.getNewBoardTexts());
            UpdateBoardResopnse updateBoardResopnse = UpdateBoardResopnse.toUpdateBoardResponse(boardEntity);
            return updateBoardResopnse;
        }
    throw new IllegalArgumentException("권한이 없습니다");
    }


}
