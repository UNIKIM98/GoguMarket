package com.goguma.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goguma.common.vo.Greeting;
import com.goguma.common.vo.HelloMessage;

@Controller
public class ChatController {
	// 웹소켓 채팅......

		@MessageMapping("/hello")
		@SendTo("/sub/greetings")
		public Greeting greeting(HelloMessage message) throws Exception {
			Thread.sleep(1000); // simulated delay
			
			return new Greeting(message.getName());
											//▲ 태그가 포함되어있으면 제거해줌(?)
			
			//db를 이용해서 할때는 @SendTo어노테이션을 지우고 service를 이용하여 insert 하면된다(?)
			//service.insert(vo);
		}

	    // 채팅 기능 테스트
		@RequestMapping("/test.do")
		public String test(Model model, HttpServletRequest request) {
			HttpSession session = request.getSession();
			String nickNm = (String)session.getAttribute("nickNm");
			System.out.println("닉넹ㅁ==="+nickNm);
			return "rsvt/test";
		}
		@RequestMapping("/chat/test.do")
		public String chatTest(Model model, HttpServletRequest request) {
			HttpSession session = request.getSession();
			String nickNm = (String)session.getAttribute("nickNm");
			System.out.println("닉넹ㅁ==="+nickNm);
			return "rsvt/chatTest";
		}
}
