    package com.example.jwtsecurity.domain.entity;

    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;
    import java.util.List;

    @Entity
    @Getter
    @NoArgsConstructor
    @Table(name = "star_table")
    public class Star {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "star_id")
        private Long id;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id",nullable = false)
        private MemberEntity member;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "board_id",nullable = false)
        private BoardEntity board;

        @Builder
        public Star(Long id, MemberEntity member, BoardEntity board) {
            this.id = id;
            this.member = member;
            this.board = board;

        }
    }
