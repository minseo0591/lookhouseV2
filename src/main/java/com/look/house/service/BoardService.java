package com.look.house.service;


import com.look.house.auth.PrincipalDetails;
import com.look.house.domain.Board;
import com.look.house.domain.Criteria;
import com.look.house.domain.Member;
import com.look.house.domain.dto.BoardDTO;
import com.look.house.domain.dto.PageDTO;
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
    private final HeartService heartService;

    /**
     * 게시글 저장
     */
    public void save(Member member, BoardDTO.Request boardDto) {

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(member.getNickName())
                .createTime(LocalDateTime.now())
                .commentCount(0)
                .heartCount(0)
                .cateId(boardDto.getCateId())
                .build();
        boardRepository.boardSave(board);
    }

    /**
     * 게시글 상세보기
     */
    public BoardDTO.Response detail(Long id, PrincipalDetails principalDetails) {
        Board board = boardRepository.findOne(id).orElseThrow(() ->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        if (principalDetails == null) {
            throw new CustomException(ErrorCode.NOT_MEMBER);
        }
        boolean heartStatus = heartService.isHeart(id, principalDetails.getMember().getNickName());
        BoardDTO.Response response = BoardDTO.Response.BoardToBoardDto(board);
        response.setHeartStatus(heartStatus);

        return response;
    }

    /**
     * 게시글 수정 (게시글이 있는지 확인 후 현재 로그인 정보와 게시글 작성자 일치여부 확인)
     */
    public void update(Long boardId, BoardDTO.Request boardDto, Member member) {

        Board board = boardRepository.findOne(boardId).orElseThrow(() ->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        if (!board.getWriter().equals(member.getNickName())) {
            throw new CustomException(ErrorCode.EDIT_ACCESS_DENIED);
        }

        board.change(boardDto.getTitle(), boardDto.getContent(),boardDto.getCateId());
        boardRepository.boardUpdate(boardId, board);
    }

    /**
     * 게시글 삭제
     */
    public void delete(Long boardId, Member member) {
        Board board = boardRepository.findOne(boardId).orElseThrow(() ->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        if (!board.getWriter().equals(member.getNickName())) {
            throw new CustomException(ErrorCode.DELETE_ACCESS_DENIED);
        }

        boardRepository.boardDelete(boardId);
    }


    /**
     * 마이페이지 내가 작성한 게시글
     */
    public BoardDTO.ResponseList boardByMe(PrincipalDetails principalDetails) {
        if (principalDetails == null) {
            throw new CustomException(ErrorCode.NOT_MEMBER);
        }

        List<Board> boards = boardRepository.findMyBoard(principalDetails.getMember().getNickName());
        List<BoardDTO.Response> response = BoardDTO.Response.ListBoardToBoardDto(boards);
        return new BoardDTO.ResponseList(response, response.size());

    }

    /*  페이징 처리 리스트
    * */
    public BoardDTO.PageResponseList pageResponseList(Criteria criteria){

        List<Board> pageList = boardRepository.findList(criteria);
        int total = boardRepository.findCount(criteria);
        PageDTO pageDTO = new PageDTO(criteria,total);
        List<BoardDTO.Response> responses = BoardDTO.Response.ListBoardToBoardDto(pageList);
        return new BoardDTO.PageResponseList(responses,pageDTO);

    }

}
