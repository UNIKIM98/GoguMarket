package com.goguma.common.vo;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SearchVO {
	private int searchNo;
	private String searchTtl;
	
	@JsonFormat(pattern = "yyyy-mm-dd HH24:MI:SS", timezone = "Asia/Seoul") //읽을땐나오는데
	@DateTimeFormat(pattern = "yyyy-mm-dd HH24:MI:SS")	// 입력할땐 9빼서 들어가짐. 쓰려면 9더해야함..
	private Date searchYmd;
	private String pstSe;
	private String stts; // 실검 상태값
	private int ttlCnt;
	
	List<String> sList;
}
