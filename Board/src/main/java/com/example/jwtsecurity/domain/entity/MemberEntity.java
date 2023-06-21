package com.example.jwtsecurity.domain.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
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

    @OneToMany(mappedBy = "memberCommentEntities")
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @OneToMany(mappedBy = "memberLikeEntities")
    private List<LikeEntity> likeEntities = new ArrayList<>();

    @Builder
    public MemberEntity(Long id, String memberName, String memberPassword, List<BoardEntity> boardEntities) {
        this.id = id;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.boardEntities = boardEntities;
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
