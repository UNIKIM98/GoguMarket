package com.goguma.deal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.deal.mapper.BoardAttachMapper;
import com.goguma.deal.service.BoardAttachService;
import com.goguma.deal.vo.BoardAttachVO;

public class BoardAttachServiceImpl implements BoardAttachService{

	@Autowired BoardAttachMapper map;
	
	@Override
	public List<MultipartFile> insert(BoardAttachVO vo) {
		// 
		return map.insert(vo);
	}
	
	@Override
	public void delete(String uuid) {
		map.delete(uuid);
		
	}

	@Override
	public List<BoardAttachVO> findByDlno(int dlNo) {
		// TODO Auto-generated method stub
		return map.findByDlno(dlNo);
	}



}
