package com.goguma.biz.vo;

import lombok.Data;

@Data
public class BizEduVdoVO {

	private String videoNo;	//동영상 정보 번호
	private String eduNo;	//강좌번호
	private String bizNo;	//가게번호
	private String vdoPath;	//영상 경로
	private String vdoTtl;	//영상제목
}
