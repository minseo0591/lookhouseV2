package com.look.house.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService  {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String id;

    @Getter
    //인증번호 생성
    private  String code;

    public MimeMessage createMessage(String to)throws MessagingException, UnsupportedEncodingException {
        code= createKey();
        MimeMessage  message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        message.setSubject("[ADMIN] 회원가입 이메일 인증번호 입니다.");  //메일 제목
        String htmlBody = generateEmailContent(code);
        message.setText(htmlBody, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,"TEST_Admin")); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    /*
          메일 발송
          sendSimpleMessage의 매개변수로 들어온 to는 인증번호를 받을 메일주소
          MimeMessage 객체 안에 내가 전송할 메일의 내용을 담아준다.
          bean으로 등록해둔 javaMailSender 객체를 사용하여 이메일 send
       */
    public String sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);

        javaMailSender.send(message); // 메일 발송

        return code; // 메일로 보냈던 인증 코드를 서버로 리턴
    }


    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }



    private String generateEmailContent(String code) {
        return "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>"
                + "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>"
                + "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\">"
                + "<table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\">"
                + "<tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">"
                + code
                + "</td></tr></tbody></table></div>";
    }


}
