package com.look.house.controller;


import com.look.house.domain.dto.MemberDTO;
import com.look.house.service.MailService;
import com.look.house.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
@Slf4j
public class MemberApiController {

    private final MemberService memberService;
    private final MailService mailService;


    @PostMapping
    public ResponseEntity join(@RequestBody @Valid MemberDTO.Join memberDto){
        memberService.join(memberDto);
        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료");
    }

    @PostMapping("/email_Check")
    public ResponseEntity mailConfirm(@RequestParam String email) throws Exception {
        String code = mailService.sendSimpleMessage(email);
        log.info("인증코드: "+code);
        return ResponseEntity.status(HttpStatus.OK).body("인증번호를 전송했습니다.");
    }


}
