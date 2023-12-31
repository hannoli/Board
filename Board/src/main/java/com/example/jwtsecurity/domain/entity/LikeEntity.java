package com.example.jwtsecurity.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "like_table")
public class LikeEntity {

    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardLikeEntity;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberLikeEntities;

    @Column
    private String likeUsers;

    @Builder
    public LikeEntity(Long id, BoardEntity boardLikeEntity,String likeUsers,MemberEntity memberLikeEntities) {
        this.id = id;
        this.boardLikeEntity = boardLikeEntity;
        this.likeUsers =likeUsers;
        this.memberLikeEntities = memberLikeEntities;
    }
    public void updateLikeUser(String newLikeUser) {
        this.likeUsers = newLikeUser;
    }
}
