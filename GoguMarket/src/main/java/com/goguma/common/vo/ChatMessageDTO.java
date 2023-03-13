package com.goguma.common.vo;

import lombok.Data;

@Data
public class ChatMessageDTO {
	private String roomId;
	private String writer;
	private String message;
}
