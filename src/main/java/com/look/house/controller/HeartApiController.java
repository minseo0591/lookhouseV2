package com.look.house.controller;

import com.look.house.auth.PrincipalDetails;
import com.look.house.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class HeartApiController {

    private final HeartService heartService;


    @PostMapping("/{boardId}")
    public ResponseEntity<?> heartSave(@PathVariable Long boardId,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean heartStatus = heartService.heartLike(boardId, principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body(heartStatus);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> heartDelete(@PathVariable Long boardId,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean heartStatus = heartService.heartUn(boardId, principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body(heartStatus);
    }


}
