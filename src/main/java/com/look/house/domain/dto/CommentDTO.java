package com.look.house.domain.dto;

import com.look.house.domain.Comment;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDTO {

    @Getter
    @ToString
    public static class Write {

        @NotBlank(message = "{NotBlank.request.content}")
        private String content;
    }


    @Getter
    @ToString
    public static class Response{
        private Long id;
        private String writer;
        private String content;
        private String createTime;


        public Response (Comment comment){
            this.id = comment.getCommentId();
            this.writer = comment.getWriter();
            this.content = comment.getContent();
            this.createTime = comment.getCreateTime().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));

        }
        public static List<Response> ListCommentTOCommentDto(List<Comment> comments){
            return comments.stream()
                    .map(comment -> new CommentDTO.Response(comment)).collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    public static class ResponseList{
        private List<CommentDTO.Response> commentList;
        private int totalCount;
    }
}
