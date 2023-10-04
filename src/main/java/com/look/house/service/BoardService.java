package com.look.house.service;


import com.look.house.domain.Board;
import com.look.house.domain.Member;
import com.look.house.domain.dto.BoardDTO;
import com.look.house.domain.dto.RequestPageDTO;
import com.look.house.domain.dto.SearchDTO;
import com.look.house.domain.paging.Pagination;
import com.look.house.repository.BoardRepository;
import com.look.house.util.error.ErrorCode;
import com.look.house.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    /**
     *  게시글 저장
     */
    public void save(Member member, BoardDTO.Request boardDto){

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(member.getNickName())
                .createTime(LocalDateTime.now())
                .commentCount(0)
                .build();
        boardRepository.boardSave(board);
    }

    /**
    *  게시글 상세보기
    */
    public BoardDTO.Response detail(Long id){
        Board board = boardRepository.findOne(id).orElseThrow(()->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        return BoardDTO.Response.BoardToBoardDto(board);
    }
    /**
     *  게시글 리스트
     */
    public List<BoardDTO.Response> list(){
        List<Board> boardList = boardRepository.findAll();
        return BoardDTO.Response.ListBoardToBoardDto(boardList);
    }

    /**
     *  게시글 수정 (게시글이 있는지 확인 후 현재 로그인 정보와 게시글 작성자 일치여부 확인)
     */
    public void update(Long boardId,BoardDTO.Request boardDto,Member member){

        Board board = boardRepository.findOne(boardId).orElseThrow(()->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        if (!board.getWriter().equals(member.getNickName())) {
            throw new CustomException(ErrorCode.EDIT_ACCESS_DENIED);
        }

        board.change(boardDto.getTitle(),boardDto.getContent());
        boardRepository.boardUpdate(boardId,board);
    }
    /**
     *  게시글 삭제
     */
    public void delete(Long boardId,Member member){
        Board board = boardRepository.findOne(boardId).orElseThrow(() ->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        if (!board.getWriter().equals(member.getNickName())) {
            throw new CustomException(ErrorCode.DELETE_ACCESS_DENIED);
        }

        boardRepository.boardDelete(boardId);
    }

    //페이지네이션 테스트 메서드
    public BoardDTO.ResponsePage pageSearchList(RequestPageDTO requestPageDTO){
        int count = boardRepository.countAll(requestPageDTO.getSearchDTO());
        log.info("count={}", count);
        // Pagination
        Pagination pagination = new Pagination(10,10);
        //페이지 계산
        pagination.changeSizes(count, requestPageDTO.getPagination().getPage());
        List<Board> boardList = boardRepository.findAll1(requestPageDTO.getSearchDTO(), pagination);
        log.info("boardList={}", boardList);
        requestPageDTO.setPagination(pagination);
        List<BoardDTO.Response> responses = BoardDTO.Response.ListBoardToBoardDto(boardList);
        return new BoardDTO.ResponsePage(responses,requestPageDTO);

    }

}
