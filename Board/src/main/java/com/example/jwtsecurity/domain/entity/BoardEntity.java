package com.example.jwtsecurity.domain.entity;


import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board_table")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String boardTitle;

    @Column
    public String boardTexts;

    @Column
    private String boardAuthor;

    @Column
    private LocalTime boardLocaltime;

 /*   @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntities;

*/
    @OneToMany(mappedBy = "boardEntity")
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @Builder
    public BoardEntity(Long id, String boardTitle, String boardTexts, String boardAuthor, LocalTime boardLocaltime) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardTexts = boardTexts;
        this.boardAuthor= boardAuthor;
        this.boardLocaltime = boardLocaltime;
     //   this.memberEntities = memberEntities;
    }
}
