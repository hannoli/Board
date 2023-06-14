package com.example.jwtsecurity.domain.repository;


import com.example.jwtsecurity.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {


    Optional<BoardEntity> findById(Long boardId);
    Optional<BoardEntity> findByBoardTitle(String boardTitle);
}
