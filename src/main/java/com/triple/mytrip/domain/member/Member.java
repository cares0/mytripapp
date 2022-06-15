package com.triple.mytrip.domain.member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
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
