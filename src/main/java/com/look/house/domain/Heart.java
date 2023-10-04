package com.look.house.domain;


import lombok.Builder;
import lombok.Data;

@Data
public class Heart {

    private Long heartId;
    private String userId;
    private Long boardId;

    @Builder
    public Heart(String userId, Long boardId) {
        this.userId = userId;
        this.boardId = boardId;
    }
}
