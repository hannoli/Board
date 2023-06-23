package com.example.jwtsecurity.domain.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board_table")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long    id;

    @Column
    private String boardTitle;

    @Column
    public String boardTexts;

    @Column
    private String boardAuthor;

    @Column
    private LocalTime boardLocaltime;

    @Column
    private Long boardView;
/*

    @ElementCollection
    private List<String> likelist;
*/

    @Column
    private Long boardLike;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntities;


    @OneToMany(mappedBy = "boardEntity")
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @OneToMany(mappedBy = "boardLikeEntity")
    private List<LikeEntity> likeEntities = new ArrayList<>();


    @OneToMany(mappedBy = "board")
    private List<Star> boardMember = new ArrayList<>();

    public List<String> getLikeUserNames() {
        return likeEntities.stream()
                .map(likeEntity -> likeEntity.getLikeUsers())
                .collect(Collectors.toList());
    }


    @Builder
    public BoardEntity(List<CommentEntity> commentEntities, Long id, String boardTitle, String boardTexts, String boardAuthor, LocalTime boardLocaltime, Long boardView, Long boardLike, MemberEntity memberEntity
            , List<LikeEntity> likeEntities,List<Star> boardMember) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardTexts = boardTexts;
        this.boardAuthor = boardAuthor;
        this.boardLocaltime = boardLocaltime;
        this.boardView = boardView;
        this.boardLike = boardLike;
        this.memberEntities = memberEntity;
        this.commentEntities = commentEntities;
        this.likeEntities = likeEntities;
        this.boardMember = boardMember;
    //   this.memberEntities = memberEntities;
    }

    public void increaseLike() {
        this.boardLike++;
    }

    public void decreaseLike() {
        this.boardLike--;
    }

    public void addLikeList(String boardAuthor, List<String> likelist) {
        likelist.add(boardAuthor);
    }

    public void removeLikeList(String boardAuthor, List<String> likelist) {
        likelist.remove(boardAuthor);
    }

    public void increaseView() {
        this.boardView++;
    }

    public void updateAuthor(String newAuthor) {
        this.boardAuthor = newAuthor;
    }

    public void updateTexts(String boardTexts) {
        this.boardTexts = boardTexts;
    }
}
