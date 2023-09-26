package com.look.house.domain;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@ToString
public class Board {
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createTime;
    private String writer;

    @Builder
    public Board(Long id, String title, String content, LocalDateTime createTime, String writer) {
        this.id = id;
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
