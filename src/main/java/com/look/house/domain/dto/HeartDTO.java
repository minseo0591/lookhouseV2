package com.look.house.domain.dto;

import lombok.Getter;
public class HeartDTO {



    @Getter
    public static class HeartResponse{

        private String userId;
        private Long boardId;


        public HeartResponse(String userId, Long boardId) {
            this.userId = userId;
            this.boardId = boardId;
        }
    }

}
