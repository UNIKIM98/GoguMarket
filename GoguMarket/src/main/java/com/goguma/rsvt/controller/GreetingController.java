package com.goguma.rsvt.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.goguma.rsvt.vo.Greeting;
import com.goguma.rsvt.vo.HelloMessage;

@Controller
public class GreetingController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
										//▲ 태그가 포함되어있으면 제거해줌(?)
		
		//db를 이용해서 할때는 @SendTo어노테이션을 지우고 service를 이용하여 insert 하면된다(?)
		//service.insert(vo);
	}
}
