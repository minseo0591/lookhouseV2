package com.look.house.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MyPageDTO {



    @Data
    public static class MyPageResponse{
        private Long commentId;
        private String content;
        private String createTime;
        private Long boardId;
        private String title;
        private int commentCount;


        public MyPageResponse(Long commentId, String content, LocalDateTime createTime, Long boardId, String title, int commentCount) {
            this.commentId = commentId;
            this.content = content;
            this.createTime = createTime.format(DateTimeFormatter.ofPattern("yy-MM-dd"));
            this.boardId = boardId;
            this.title = title;
            this.commentCount = commentCount;
        }


    }

    @Data
    @AllArgsConstructor
    public static class MyPageResponseList{
        private List<MyPageResponse> myPageResponseList;
        private int totalCount;
    }



}
