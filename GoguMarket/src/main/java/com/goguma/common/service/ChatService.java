package com.goguma.common.service;

import java.util.List;

import com.goguma.common.vo.ChatListVO;

public interface ChatService {
	//채팅목록
	List<ChatListVO> selectChatList();
}
