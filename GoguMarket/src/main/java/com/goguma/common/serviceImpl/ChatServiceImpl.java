package com.goguma.common.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.common.mapper.ChatMapper;
import com.goguma.common.service.ChatService;
import com.goguma.common.vo.ChatListVO;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired ChatMapper map;
	
	//채팅목록
	@Override
	public List<ChatListVO> selectChatList() {
		return map.selectChatList();
	}
	
}
