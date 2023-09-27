package com.look.house.domain.dto;

import com.look.house.domain.Comment;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.format.DateTimeFormatter;

public class CommentDTO {

    @Getter
    @ToString
    public static class Write {

        @NotBlank(message = "{NotBlank.request.content}")
        private String content;
    }



}
