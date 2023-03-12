package com.goguma.common.mapper;

import java.util.List;

import com.goguma.common.vo.ChatListVO;

public interface ChatMapper {
	//채팅목록
	List<ChatListVO> selectChatList();
}
