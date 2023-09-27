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

}
