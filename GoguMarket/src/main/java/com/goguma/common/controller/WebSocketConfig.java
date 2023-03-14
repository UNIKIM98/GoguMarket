package com.goguma.common.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker	//메시지 브로커가 지원하는 ‘WebSocket 메시지 처리’를 활성화한다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	 
	 @Override
	  public void configureMessageBroker(MessageBrokerRegistry config) {  //application 내부에서 사용할 path를 지정한다.
	    config.enableSimpleBroker("/sub");				
	    		//▲ 구독신청(메시지를 받겠다는 신청) : SimpleBroker를 등록함. 그게먼데? => 해당경로를 subscribe하는 client에게 메시지 전달
	    config.setApplicationDestinationPrefixes("/pub");	//메시지를 보냄 (client에서 send요청을 처리함)
	  }

	  @Override
//	  public void registerStompEndpoints(StompEndpointRegistry registry) {
//	    registry.addEndpoint("/chatServer").withSockJS();
//	    	//웹소켓 서버 접속요청하는 url(클라이언트가 WebSocket 핸드셰이크를 위해 연결되어야 하는 URL)
//	  }
	  public void registerStompEndpoints(StompEndpointRegistry registry) {
	        registry.addEndpoint("/stomp/chat")
	                .setAllowedOrigins("http://localhost:80")
	                .withSockJS();
	    }
}
