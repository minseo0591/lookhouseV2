package com.look.house.controller;

import com.look.house.util.error.ErrorResponse;
import com.look.house.util.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice   //모든 컨트롤러에서 발생할 수 있는 예외를 잡아 처리할 수 있다.
public class ExceptionController {



    @ExceptionHandler(CustomException.class)
    public ResponseEntity customExceptionHandler(CustomException exception){
        return ResponseEntity.status(exception.getResponse().getHttpStatus())
                .body(exception.getResponse());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity notValidExceptionHandler(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ErrorResponse> errorResponseList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .field(fieldError.getField())
                    .code(fieldError.getCode())
                    .message(fieldError.getDefaultMessage())
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
            errorResponseList.add(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseList);
    }
}
