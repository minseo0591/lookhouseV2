package com.look.house.util.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;



@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String field;
    private String code;
    private String message;
    private HttpStatus httpStatus;

    @Builder
    public ErrorResponse(String field,String code,String message, HttpStatus httpStatus) {
        this.field =field;
        this.code =code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.field= errorCode.getField();
        this.code= errorCode.getCode();
        this.message=errorCode.getMessage();
        this.httpStatus=errorCode.getHttpStatus();
    }


}
