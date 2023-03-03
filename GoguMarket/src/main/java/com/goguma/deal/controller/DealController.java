package com.goguma.deal.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.service.AtchService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.service.SearchService;
import com.goguma.common.vo.AtchVO;
import com.goguma.common.vo.SearchVO;
import com.goguma.deal.mapper.DealMapper;
import com.goguma.deal.service.DealReviewService;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;
import com.goguma.deal.vo.Paging;

@Controller

public class DealController {

	@Autowired
	private DealMapper dealMapper;
	@Autowired
	private DealService dealService;
	@Autowired
	private DealReviewService RvService;
	@Autowired
	private AtchService attachService;
	@Autowired
	private CommonCodeService codeService;
	@Autowired
	private SearchService sService;

	
	@RequestMapping("/dealMain") // 중고거래 메인 페이지
	public String dealMain(Paging paging, Model model, SearchVO scvo, @ModelAttribute("dsvo") DealSearchVO svo) {
		paging.setPageUnit(8); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수	
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(dealMapper.getcountTotal(svo));
		
		model.addAttribute("lists", dealMapper.dealListSelect(svo));
		model.addAttribute("category",codeService.codeList("002")); // string 공통코드 넣으면 모든테이블이나옴 저기서 나는 common_detail_code만 들고오면됨
		
		model.addAttribute("word", sService.getPopularWord());
		
		sService.insertSearch(scvo); // 검색어저장ㅅㅂ
		System.out.println(sService.getPopularWord()+"wkfs나오낫");
		return "deal/dealMain";
	}
	

	
	@RequestMapping("/dealList") // 판매상품 전체 조회
	public String dealListSelect(Model model, @ModelAttribute("dsvo") DealSearchVO svo, Paging paging) {

		paging.setPageUnit(5); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		paging.setTotalRecord(dealMapper.getcountTotal(svo));

		
		model.addAttribute("lists", dealMapper.dealListSelect(svo));
		model.addAttribute("category",codeService.codeList("002")); // string 공통코드 넣으면 모든테이블이나옴 저기서 나는 common_detail_code만 들고오면됨
		return "deal/dealList"; // 뷰페이지명
	}
	
	
	// 판매상품 단건 조회 => detail에 쓸려고
	@RequestMapping("/dealdetail/{dlNo}")
	public String getDeal(@PathVariable String dlNo, Model model) {
		System.out.println("=================" + dlNo);

		int cnt = dealService.dealHitUpdate(dlNo);
		int id = dealService.getId(dlNo);

		model.addAttribute("deal", dealService.getDeal(dlNo));	// 단건
		model.addAttribute("list", dealService.getDealSeller(dlNo));//여러건
		model.addAttribute("ct", dealService.getDealCtgry(dlNo));
		
		model.addAttribute("file",attachService.selectAtch(id));
		System.out.println(attachService.selectAtch(id)+"파일의정보");
		//	List<AtchVO> selectAtch(int atchId);
		
		return "deal/dealdetail";
	}
	

	@RequestMapping("/dealSellerpage/{dlNo}")
	public String getDealSeller(@PathVariable String dlNo, Model model) {
		int id = dealService.getId(dlNo);
		
		model.addAttribute("deal", dealService.getDeal(dlNo)); // 단건
		model.addAttribute("list", dealService.getDealSeller(dlNo));//여러건
		model.addAttribute("review", RvService.getDealRv(dlNo));//여러건
		
		model.addAttribute("file",attachService.selectAtch(id));
		
		return "deal/dealSellerPage";
	}
	
	@RequestMapping("/dealform") // 딜폼창확인
	public String dealform(Model model) {
		model.addAttribute("category",codeService.codeList("002"));
		return "deal/dealform";
	}

	@RequestMapping("/dealformsubmit") // 딜폼창확인
	public String dealform(DealVO vo, List<MultipartFile> files) {
		System.out.println(vo.getAtchId()+"메롱");
		int atchId = attachService.insertFile(files);
		
		if(atchId > 0) {
			vo.setAtchId(atchId);
		}
		dealService.insertDeal(vo);
		return "redirect:dealList";
	}
	// 판매상품 등록 : 계좌정보와 아이디값이 없으면 등록할 수 없다 => @PostMapping("/deal/{acntno}/{id}")
	// 일단 폼데이타로 부메랑에서 들어가는지 확인해보자
	@PostMapping("/deal")
	@ResponseBody
	public DealVO insertDeal(DealVO vo, MultipartFile file) {
		return vo;

	}

	/*
	 * // 판매상품 수정
	 * 
	 * @PutMapping("/deal")
	 * 
	 * @ResponseBody public DealVO updateDeal(DealVO vo) {
	 * dealService.updateDeal(vo); return vo; }
	 */
	// 판매상품 삭제
	public Map<String, Object> deleteDeal(@PathVariable String id) {
		dealService.deleteDeal(id);
		return Collections.singletonMap("result", "success delete");
	}

	/*
	 * // 걍 상세정보 볼려고 띄우는거 d이거왜 dealDetail 대문자는 인식이안되냐?;
	 * 
	 * @RequestMapping("/dealdetail") // public String dealdetail() { return
	 * "deal/dealdetail"; }
	 */

	

}
