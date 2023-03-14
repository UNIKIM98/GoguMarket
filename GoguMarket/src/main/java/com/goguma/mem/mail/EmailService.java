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
    
    public void sendVerificationMail(String to, int token, String emailTtl) throws MessagingException {
    	
        // ↓ 이메일 제목, 내용 등 설정할 수 있는 클래스
    	MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String text = "인증번호는 "+token+"입니다. 인증 화면으로 돌아가서 인증을 마무리해주세요 :D";
                
        helper.setTo(to); //받는 사람 이메일 주소
        helper.setText(text, true); //이메일 본문
        helper.setSubject(emailTtl); //이메일 제목
        mailSender.send(message);
    }
}
