package com.goguma.deal.vo;

import lombok.Data;

@Data
public class BoardAttachVO {

	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private int fileSize; // 파일크기
	
	private int dlNo; // 딜보의 글번호 들고와야함
}
