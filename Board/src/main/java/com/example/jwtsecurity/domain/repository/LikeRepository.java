package com.example.jwtsecurity.domain.repository;

import com.example.jwtsecurity.domain.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {

    //void delete(BoardEntity boardLikeEntity);
    Optional<LikeEntity> findByLikeUsers(String likeUsers);
    void deleteByLikeUsers(String likeUsers);
}
