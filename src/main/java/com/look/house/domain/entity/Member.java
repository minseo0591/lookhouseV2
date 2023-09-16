package com.look.house.domain.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;

    private String nickName;

    private String username;

    private String password;

    private String email;

    private String role;

    private String provider;

    private String providerId;






}
