package com.look.house.domain.dto;

import com.look.house.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        @NotBlank(message = "{NotBlank.request.title}")
        private String title;

        @NotBlank(message = "{NotBlank.request.content}")
        private String content;

        public Request(){
            //기본생성자 없으면 실행안됨
        }
    }



    @Data
    @NoArgsConstructor
    public static class Response{

        private Long id;
        private String title;
        private String content;
        private String writer;
        private String createTime;
        private int commentCount;

        public Response(Board board) {
            this.id= board.getId();
            this.title= board.getTitle();
            this.content = board.getContent();
            this.writer = board.getWriter();
            this.createTime=board.getCreateTime().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
            this.commentCount= board.getCommentCount();
        }

        public static BoardDTO.Response BoardToBoardDto(Board board){
            return new Response(board);
        }

        public static List<Response> ListBoardToBoardDto(List<Board> boards){
            return boards.stream()
                    .map(board -> new BoardDTO.Response(board)).collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    public static class ResponseList{
        private List<BoardDTO.Response> boardList;
        private int totalCount;
//        private  pageList;
    }


}