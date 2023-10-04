package com.look.house.controller;

import com.look.house.auth.PrincipalDetails;
import com.look.house.domain.dto.BoardDTO;
import com.look.house.domain.dto.MyPageDTO;
import com.look.house.service.BoardService;
import com.look.house.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageApiController {

    private final BoardService boardService;
    private final MyPageService myPageService;

    @GetMapping("/board")
    public ResponseEntity boardByMe(@AuthenticationPrincipal PrincipalDetails principalDetails){
        BoardDTO.ResponseList responseList = boardService.boardByMe(principalDetails);
        if(responseList.getTotalCount()==0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @GetMapping("/commentAndBoard")
    public ResponseEntity findCommentAndBoard(@AuthenticationPrincipal PrincipalDetails principalDetails){
        MyPageDTO.MyPageResponseList myPageResponseList = myPageService.myPageResponseList(principalDetails);
        System.out.println(myPageResponseList.getTotalCount());
        if(myPageResponseList.getTotalCount() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(myPageResponseList);
    }


    @GetMapping("/heartAndBoard")
    public ResponseEntity findHeartAndBoard(@AuthenticationPrincipal PrincipalDetails principalDetails){
        BoardDTO.ResponseList responseList = myPageService.myPageHeartList(principalDetails);
        if(responseList.getTotalCount()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }



}
