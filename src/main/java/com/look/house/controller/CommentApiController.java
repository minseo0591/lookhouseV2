package com.look.house.controller;


import com.look.house.auth.PrincipalDetails;
import com.look.house.domain.dto.CommentDTO;
import com.look.house.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    public ResponseEntity saveComment(@PathVariable Long id,
                                      @AuthenticationPrincipal PrincipalDetails principalDetails,
                                      @RequestBody @Valid CommentDTO.Write commentDTO){
        commentService.saveComment(id,principalDetails,commentDTO);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 작성 확인");
    }

    @GetMapping("/{id}/comment")
    public ResponseEntity listComment(@PathVariable Long id){
        CommentDTO.ResponseList responseList = commentService.listComment(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @PatchMapping("/{id}/comment/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long id,
                                        @PathVariable Long commentId,
                                        @AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @RequestBody @Valid CommentDTO.Write commentDTO){
        commentService.updateComment(commentId,principalDetails,commentDTO);
        return ResponseEntity.status(HttpStatus.OK).body("수정 완료");
    }

    @DeleteMapping("/{id}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long id,
                                        @PathVariable Long commentId,
                                        @AuthenticationPrincipal PrincipalDetails principalDetails){
        commentService.deleteComment(commentId,principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }
}
