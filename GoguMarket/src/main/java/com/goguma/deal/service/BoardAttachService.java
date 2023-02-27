package com.goguma.deal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.goguma.deal.vo.BoardAttachVO;

public interface BoardAttachService {
	
	public List<MultipartFile> insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByDlno(int dlNo);
}
