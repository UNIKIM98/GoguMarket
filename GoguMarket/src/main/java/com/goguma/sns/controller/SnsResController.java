package com.goguma.sns.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goguma.common.service.AtchService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.AtchVO;
import com.goguma.common.vo.CommonCodeVO;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;
import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@RestController
public class SnsResController {
	@Autowired
	SnsService service;

	@Autowired
	AtchService aservice;

	@Autowired
	MemService member;

	@Autowired
	CommonCodeService common;

	@GetMapping("/keyValue") // > 공통코드 조희
	public Map<String, Object> keyValue() {

		Map<String, Object> map = new HashMap<String, Object>();

		
		// > LIST 선언
		List<CommonCodeVO> pstSe = new ArrayList(); 		 
		List<CommonCodeVO> selist = new ArrayList(); 		
		List<CommonCodeVO> codelist = new ArrayList(); 		 
		List<CommonCodeVO> searchlist = new ArrayList();	

		pstSe = common.codeList("001");						// > 거래 카테고리
		selist = common.codeList("003");					// > 회원 구분
		codelist = common.codeList("004");					// > 회원 상태
		searchlist = common.codeList("009");				// > 검색 구분

		// > MAP 에 담음 
		map.put("pstSe", pstSe);
		map.put("selist", selist);
		map.put("codelist", codelist);
		map.put("searchlist", searchlist);

		return map;
	}

	
	@GetMapping("/goguma/selectSnsList") // > 동네생활 전체 게시글 출력
	public List<SnsVO> getSnsList(SnsVO vo) {

		System.out.println(vo);
		List<SnsVO> result = service.selectSnsList(vo);

		return result;

	}

	@GetMapping("/goguma/selectSns") // > 동네생활 게시글 단건 조회
	public Map<String, Object> selectSns(int id) {

		service.snsHitUpdate(id);   		// > 게시글 조화수 증가 METHOD
		SnsVO vo = service.selectSns(id);   // > 게시글 단건 조회에서 VO 에 담음

		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sns", vo);
		map.put("atch", aservice.selectAtch(vo.getAtchId()));

		return map;

	}

	@GetMapping("/goguma/getUser")   // > 동네생활 페이지 입장시 현재 세션 유저 정보를 조회하는 메소드
	public Map<String, Object> getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();	// > 세션 요청

		String userId = (String) session.getAttribute("userId"); // > 새션정보에 담긴 유저 아이디 를 자져옴

		MemVO vo = new MemVO();

		vo.setUserId(userId); // > 회원관련 VO에 유저 아이디를 담음

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("mem", member.selectUser(vo)); // > 유저 정보 조회

		return map;

	}

	@PostMapping("/goguma/insertSns") // > 동네생활 게시글 등록 메소드
	public ModelAndView insertSns(SnsVO vo, AtchVO avo, List<MultipartFile> files) {

		ModelAndView mv = new ModelAndView("redirect:snsMain"); // > 돌아갈 좌표

		int atchId = aservice.insertFile(files); // > 첨부 파일 등록

		if (atchId > 0) { // > 첨부파일 등록 성공하면 동네생활 VO에 ATCH_ID를 저장
			vo.setAtchId(atchId);
		}

		service.insertSns(vo); // > 게시물 등록 

		return mv;
	}

	@GetMapping("/my/selectMySns") // > 마이페지 > 내글 > 개인 동네생활 조회 
	public List<SnsVO> mySns(HttpServletRequest request) {

		HttpSession session = request.getSession(); // > 세션 요청

		String userId = (String) session.getAttribute("userId"); // > 세션 아이디 저장

		List<SnsVO> list = service.selectPerSns(userId); // > 해당 유저의 SNS 조회후 리스트 반환

		return list;
	}

	@GetMapping("/goguma/deleteSns") // > 동네생활 게시글 삭제
	public int deleteSns(SnsVO vo, AtchVO avo, List<MultipartFile> files, HttpServletResponse response) {

		vo = service.selectSns(vo.getSnsNo()); 							// > 게시글 번호를 가지고 해당 게시글 정보를 조회
		
		/* System.out.println(vo + " => 삭제할 글 정보"); */

		List<AtchVO> snsList = service.selectSnsAtch(vo.getSnsNo()); 	// > 해당 게시글의 이미지 정보를 조회

		/* System.out.println(snsList + " => 삭제할 첨부파일들 정보"); */
		
		int delAtch = aservice.deleteFile(snsList); 				 	// > 해당 게시글의 파일 삭제
		/* System.out.println("첨부파일 삭제했으면 1 이상 => " + delAtch); */	

		int delSns = service.deleteSns(vo);								// > 해당 게시글 삭제
		/* System.out.println("게시글 삭제했으면 1 => " + delSns); */

		return delSns;
	}

}
