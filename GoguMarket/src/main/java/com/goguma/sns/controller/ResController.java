package com.goguma.sns.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goguma.common.service.AtchService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.AtchVO;
import com.goguma.common.vo.CommonCodeVO;
import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@RestController
public class ResController {
	@Autowired
	SnsService service;

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

		map.put("pstSe",pstSe);
		map.put("selist", selist);
		map.put("codelist", codelist);
		map.put("searchlist", searchlist);

		return map;
	}

	@GetMapping("/selectSnsList")
	public List<SnsVO> getSnsList(SnsVO vo) {

		System.out.println(vo);
		List<SnsVO> result = service.selectSnsList(vo);

		return result;

	}

	@GetMapping("/selectSns")
	public Map<String, Object> selectSns(int id) {
		System.out.println(id);

		service.snsHitUpdate(id);
		SnsVO vo = service.selectSns(id);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sns", vo);
		map.put("atch", aservice.selectAtch(vo.getAtchId()));

		return map;

	}

	@PostMapping("/insertSns")
	public ModelAndView insertSns(SnsVO vo, AtchVO avo, List<MultipartFile> files) {

		ModelAndView mv = new ModelAndView("redirect:snsMain");

		System.out.println(vo);
		int atchId = aservice.insertFile(files);
		System.out.println(vo);

		if (atchId > 0) {
			vo.setAtchId(atchId);
		}

		service.insertSns(vo);
//		aservice.fileUpload(null);

		return mv;
	}

	@GetMapping("/sns/deleteSns/{snsNo}")
	public String deleteSns(SnsVO vo, AtchVO avo, List<MultipartFile> files,HttpServletResponse response) {

		vo = service.selectSns(vo.getSnsNo());
		System.out.println(vo + " => 삭제할 글 정보");

		int snsNO = vo.getSnsNo();

		List<AtchVO> snsList = service.selectSnsAtch(snsNO);

		System.out.println(snsList + " => 삭제할 첨부파일들 정보");

		int delSns = service.deleteSns(vo);

		System.out.println("게시글 삭제했으면 1 => " + delSns);

		int delAtch = aservice.deleteFile(snsList);

		System.out.println("첨부파일 삭제했으면 1 이상 => " + delAtch);

		try {
			if (delSns > 0) {
				System.out.println("왔니..delAcut 안쪽임");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script language='javascript'>");
				out.println("alert('[삭제완료] 게시글 삭제가 정상적으로 완료되었습니다. :D '); location.href='/goguma/auctList';");

				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('[삭제실패] 게시글 삭제 중 오류가 발생하였습니다 :( '); location.href='/auctList';");
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return "myPages/mySns";
	}

}
