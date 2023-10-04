package com.look.house.util.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    ID_NOT_FOUND("id","id.notfound","해당 id를 찾을 수없습니다.",HttpStatus.BAD_REQUEST),
    EDIT_ACCESS_DENIED("edit","edit.mismatch","수정 권한이 없습니다.",HttpStatus.BAD_REQUEST),
    DELETE_ACCESS_DENIED("delete","delete.mismatch","삭제 권한이 없습니다.",HttpStatus.BAD_REQUEST),
    JOIN_CHECK_EMAIL("checkEmail","email.mismatch","인증번호가 아닙니다.",HttpStatus.BAD_REQUEST),
    NOT_MEMBER("access","access.denied","로그인을 해주세요",HttpStatus.BAD_REQUEST),
    MEMBER_HEART_COUNT("heart","heart_count","중복 요청을 할 수 없습니다.",HttpStatus.BAD_REQUEST);
    private String field;
    private String code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(String field, String code, String message, HttpStatus httpStatus) {
        this.field = field;
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
