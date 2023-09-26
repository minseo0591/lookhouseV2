package com.look.house.domain;


import com.look.house.domain.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {

    private Long id;
    private String email;  // 값 없어도됨
    private String password; //Random 으로 넣어줄 예정
    private String nickName;  // 사용자 중복 안되게끔 사용자명
    private Role role = Role.ROLE_USER;;  //기본 권한은 그냥 ROLE_USER
    private String provider;  // google, facebook, naver
    private LocalDateTime createDate;  //생성날짜

    @Builder
    public Member(String email, String password, String nickName, LocalDateTime createDate) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.provider = "lookHouse";
        this.createDate = createDate;
    }

    public Member() {
    }
}
