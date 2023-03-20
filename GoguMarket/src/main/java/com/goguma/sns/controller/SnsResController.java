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
	MemService member;

	@Autowired
	AtchService aservice;

	@Autowired
	CommonCodeService common;

	@GetMapping("/keyValue")
	public Map<String, Object> keyValue() {
//		System.out.println("gdgd");
		Map<String, Object> map = new HashMap<String, Object>();

		List<CommonCodeVO> pstSe = new ArrayList();
		List<CommonCodeVO> selist = new ArrayList();
		List<CommonCodeVO> codelist = new ArrayList();
		List<CommonCodeVO> searchlist = new ArrayList();

		pstSe = common.codeList("001");
		selist = common.codeList("003");
		codelist = common.codeList("004");
		searchlist = common.codeList("009");

		map.put("pstSe", pstSe);
		map.put("selist", selist);
		map.put("codelist", codelist);
		map.put("searchlist", searchlist);

		return map;
	}

	@GetMapping("/goguma/selectSnsList")
	public List<SnsVO> getSnsList(SnsVO vo) {

		System.out.println(vo);
		List<SnsVO> result = service.selectSnsList(vo);

		return result;

	}

	@GetMapping("/goguma/selectSns")
	public Map<String, Object> selectSns(int id) {
		System.out.println(id);

		service.snsHitUpdate(id);
		SnsVO vo = service.selectSns(id);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sns", vo);
		map.put("atch", aservice.selectAtch(vo.getAtchId()));

		return map;

	}

	@GetMapping("/goguma/getUser")
	public Map<String, Object> getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("userId");

		MemVO vo = new MemVO();

		vo.setUserId(userId);

		System.out.println(vo);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("mem", member.selectUser(vo));

		return map;

	}

	@PostMapping("/goguma/insertSns")
	public ModelAndView insertSns(SnsVO vo, AtchVO avo, List<MultipartFile> files) {

		System.out.println("여기까지 옴");
		ModelAndView mv = new ModelAndView("redirect:snsMain");

		System.out.println(vo + "1");
		int atchId = aservice.insertFile(files);
		System.out.println(vo);

		if (atchId > 0) {
			vo.setAtchId(atchId);
		}

		service.insertSns(vo);
//		aservice.fileUpload(null);

		return mv;
	}

	@GetMapping("/my/selectMySns")
	public List<SnsVO> mySns(HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("userId");

		System.out.println(userId);

		List<SnsVO> list = service.selectPerSns(userId);

		return list;
	}

	@GetMapping("/goguma/deleteSns")
	public int deleteSns(SnsVO vo, AtchVO avo, List<MultipartFile> files, HttpServletResponse response) {
		System.out.println(vo);

		vo = service.selectSns(vo.getSnsNo());
		System.out.println(vo + " => 삭제할 글 정보");

		List<AtchVO> snsList = service.selectSnsAtch(vo.getSnsNo());

		System.out.println(snsList + " => 삭제할 첨부파일들 정보");

		int delAtch = aservice.deleteFile(snsList);
		System.out.println("첨부파일 삭제했으면 1 이상 => " + delAtch);

		int delSns = service.deleteSns(vo);
		System.out.println("게시글 삭제했으면 1 => " + delSns);

		return delSns;
	}

}
