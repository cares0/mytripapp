package com.triple.mytrip.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String phone;
    private String name;
    private String nickname;
    private String inviteCode;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
