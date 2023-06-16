package com.example.jwtsecurity.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String commentText;

    @Column
    public String commentWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberCommentEntities;


    @Builder
    public CommentEntity(Long id, String commentText, String commentWriter,BoardEntity boardEntity,MemberEntity memberCommentEntities) {
        this.id = id;
        this.commentText = commentText;
        this.commentWriter = commentWriter;
        this.boardEntity = boardEntity;
        this.memberCommentEntities = memberCommentEntities;
    }

    public void updateCommentUser(String newCommentUser) {
        this.commentWriter = newCommentUser;
    }
}

