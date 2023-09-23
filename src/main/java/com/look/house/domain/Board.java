package com.look.house.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Board {
    private Long boardId;

    private String title;
    private String content;
    private LocalDateTime createTime;
    private String writer;

    @Builder
    public Board(Long boardId, String title, String content, LocalDateTime createTime, String writer) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.writer = writer;
    }


    public void change(String title,String content){
        this.title= title;
        this.content =content;
    }
}
