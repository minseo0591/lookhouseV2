package com.look.house.controller;

import com.look.house.auth.PrincipalDetails;
import com.look.house.domain.Criteria;
import com.look.house.domain.dto.BoardDTO;
import com.look.house.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Slf4j
public class BoardApiController {

    private final BoardService boardService;

    /**
     *  게시글 등록하기
     */
    @PostMapping
    public ResponseEntity saveBoard(@RequestBody @Valid BoardDTO.Request boardDto,@AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("principal ={}",principalDetails);
        log.info("boardDto ={}",boardDto);
        boardService.save(principalDetails.getMember(), boardDto);
        return ResponseEntity.status(HttpStatus.OK).body("작성 완료");
    }

     /* @게시글 상세보기 페이지
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO.Response> detailBoard(@PathVariable Long id,
                                                         @AuthenticationPrincipal PrincipalDetails principalDetails){
        BoardDTO.Response detail = boardService.detail(id,principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body(detail);
    }

    /**
     * @게시글 수정 ID값 필수
     */
    @PatchMapping("/{id}")
    public ResponseEntity updateBoard (@PathVariable("id") Long boardId,
                                       @RequestBody @Valid BoardDTO.Request boardDto,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.update(boardId,boardDto,principalDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body("수정 OK");
    }

    /**
     * @게시글 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable("id") Long boardId,
                                      @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.delete(boardId,principalDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body("삭제 OK");
    }

   /**
     * 게시글 리스트
     */

    @GetMapping
    public ResponseEntity pageList(@RequestBody Criteria criteria){
        BoardDTO.PageResponseList pageResponseList = boardService.pageResponseList(criteria);
        return ResponseEntity.status(HttpStatus.OK).body(pageResponseList);
    }

}
