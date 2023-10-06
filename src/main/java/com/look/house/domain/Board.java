package com.look.house.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@ToString
public class Board {
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createTime;
    private String writer;

    private int commentCount;
    private int heartCount;
    private int cateId;
    @Builder
    public Board(Long id, String title, String content, LocalDateTime createTime, String writer,int commentCount,int heartCount, int cateId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.writer = writer;
        this.commentCount = commentCount;
        this.heartCount = heartCount;
        this.cateId = cateId;
    }


    public void change(String title,String content,int category){
        this.title= title;
        this.content =content;
        this.cateId =category;
    }

}
