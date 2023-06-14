package com.example.jwtsecurity.domain.entity;


import javax.persistence.*;
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
/*

    @OneToMany(mappedBy = "memberEntities")
    private List<BoardEntity> boardEntities = new ArrayList<>();
*/

    @Builder
    public MemberEntity(Long id, String memberName, String memberPassword) {
        this.id = id;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
    }



}
