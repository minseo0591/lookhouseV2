package com.look.house.domain.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

public class MemberDTO {

    @Getter
    @ToString
    public static class Join{

       @NotBlank(message = "아이디는 필수 입니다.")
        private String email;

       @NotBlank(message = "인증번호를 입력해주세요")
       private String checkCode;

        @NotBlank(message = "비밀번호는 필수 입니다.")
        private String password;

        @NotBlank(message = "닉네임은 필수 입니다.")
        private String nickName;

    }
}
