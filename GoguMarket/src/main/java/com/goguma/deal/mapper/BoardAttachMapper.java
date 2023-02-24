package com.goguma.deal.mapper;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.goguma.deal.vo.BoardAttachVO;

public interface BoardAttachMapper {

	//public void insert(BoardAttachVO vo);
	public List<MultipartFile> insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByDlno(int dlNo);
}
