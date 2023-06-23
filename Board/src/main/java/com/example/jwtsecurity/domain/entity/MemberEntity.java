package com.example.jwtsecurity.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String memberName;

    @Column
    private String memberPassword;


    @JsonIgnore
    @OneToMany(mappedBy = "memberEntities")
    private List<BoardEntity> boardEntities = new ArrayList<>();
@JsonIgnore
    @OneToMany(mappedBy = "memberCommentEntities")
    private List<CommentEntity> commentEntities = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "memberLikeEntities")
    private List<LikeEntity> likeEntities = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Star> boardMember = new ArrayList<>();

    @Builder
    public MemberEntity(Long id, String memberName, String memberPassword,List<Star> boardMember) {
        this.id = id;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
this.boardMember =boardMember;
    }


    public void updateName(String newName) {
        this.memberName = newName;
        for (BoardEntity boardEntity : boardEntities) {
            boardEntity.updateAuthor(newName);
        }
        for (CommentEntity commentEntity : commentEntities) {
            commentEntity.updateCommentUser(newName);
        }
        for (LikeEntity likeEntity : likeEntities) {
            likeEntity.updateLikeUser(newName);
        }
    }

    public void updatePassword(String newPassword) {
        this.memberPassword = newPassword;
    }
}
