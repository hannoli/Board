package com.example.jwtsecurity.domain.repository;

import com.example.jwtsecurity.domain.entity.BoardEntity;
import com.example.jwtsecurity.domain.entity.MemberEntity;
import com.example.jwtsecurity.domain.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StarRepository extends JpaRepository<Star,Long> {
void deleteByMemberAndBoard(MemberEntity member, BoardEntity board);

}

