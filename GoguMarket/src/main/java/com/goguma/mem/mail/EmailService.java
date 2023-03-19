package com.goguma.mem.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	// ↓이메일 전송 인터페이스
	@Autowired
    private JavaMailSender mailSender;
    
    public void sendVerificationMail(String to, String token, String emailTtl) throws MessagingException {
    	
        // ↓ 이메일 제목, 내용 등 설정할 수 있는 클래스
    	MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        String text = "";
        text+= "<div style=\"border: 2px solid #C04A82; padding: 20px;\">";
        text+="<div style=\"text-align:center; font-size:20px; font-weight:bold;\">";
        text+="<span style=\"color:#C04A82; font-weight:bold;\">고</span>기로 갈게요,<span style=\"color:#C04A82; font-weight:bold;\">구</span>매 가능한가요?</div>";
        text+="<div style=\"text-align:center; font-size:16px; color:#000; margin-top:20px;\">안녕하세요, 고구마켓입니다.</div>";
        text+="<div style=\"text-align:center; font-size:16px; color:#000; margin-top:20px;\">아래의 코드를 복사한 후, 인증화면으로 돌아가서 인증을 마무리해주세요 :D</div>";
        text+="<div style=\"border:1px solid #C04A82; padding:10px; margin-top:20px; text-align:center; font-size:18px; color:#C04A82; font-weight:bold;\">인증번호 : <span style=\\\"color:#FCD052; font-weight:bold;\\\">"+token+"</span> </div>";
        text+="</div>";
        
        helper.setTo(to); //받는 사람 이메일 주소
        helper.setText(text, true); //이메일 본문
        helper.setSubject(emailTtl); //이메일 제목
        mailSender.send(message);
    }
}
