package com.look.house.service;


import com.look.house.domain.Member;
import com.look.house.domain.dto.MemberDTO;
import com.look.house.repository.MemberRepository;
import com.look.house.util.error.ErrorCode;
import com.look.house.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;

    public void join(MemberDTO.Join memberDto){
        String encode = bCryptPasswordEncoder.encode(memberDto.getPassword());
        String checkCode = memberDto.getCheckCode();

        if(checkCode.equals(mailService.getCode())){
            Member member = Member.builder()
                    .email(memberDto.getEmail())
                    .password(encode)
                    .nickName(memberDto.getNickName())
                    .createDate(LocalDateTime.now())
                    .build();
            memberRepository.memberSave(member);
        }else{
            throw new CustomException(ErrorCode.JOIN_CHECK_EMAIL);
        }

    }

}
