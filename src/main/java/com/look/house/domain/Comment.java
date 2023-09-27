package com.look.house.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Comment {

    private Long commentId;
    private Long boardId;

    private String content;
    private String writer;
    private LocalDateTime createTime;

    @Builder
    public Comment(Long commentId,Long boardId, String content, String writer, LocalDateTime createTime) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.content = content;
        this.writer = writer;
        this.createTime = createTime;
    }
}
