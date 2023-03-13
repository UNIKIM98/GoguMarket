package com.goguma.biz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goguma.biz.mapper.BizMemMapper;
import com.goguma.biz.service.BizDangolService;
import com.goguma.biz.service.BizMemService;
import com.goguma.biz.service.BizNewsService;
import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizSearchVO;
import com.goguma.biz.vo.PagingVO;
import com.goguma.common.service.CommonCodeService;
import com.goguma.rsvt.service.BizMenuService;
import com.goguma.rsvt.service.RsvtRvService;
import com.goguma.rsvt.vo.RsvtRvVO;
import com.goguma.rsvt.vo.RsvtVO;

@Controller
public class BizController {


	@Autowired BizMemService memService; 	// 가게정보
	@Autowired private BizNewsService newsService; 	// 가게소식 들고오기 위함
	@Autowired private BizMenuService menuService;	//메뉴 들고오기 위함
	@Autowired private RsvtRvService rvService;		//리뷰 들고오기 위함
	@Autowired BizMemMapper bizMapper;				//페이징 검색 하기 위함
	@Autowired CommonCodeService codeService;		//공통코드
	@Autowired BizDangolService dangolService;		//단골


//	// 동네가게 예약 메인(book01).
//	@RequestMapping("/bookmain")
//	public String getBizList(Model model) {
//		model.addAttribute("lists", memService.getBizList());
//		return "rsvt/book01";
//	}

	// 동네가게 예약 메인 페이징
	@GetMapping("/goguma/bookmain")
	public String bizListPage(Model model, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo, BizMemVO vo) {
		pvo.setPageUnit(5);		//한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5);		//한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)
		
		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());
		
		pvo.setTotalRecord(bizMapper.bizListCnt(bvo));
		
		model.addAttribute("lists", bizMapper.bizListPage(bvo));

		//＃ 가게 전체 셀렉트
		List<BizMemVO> lists = bizMapper.getBizList();
		
		//카테고리 리스트
		model.addAttribute("ctgry", codeService.codeList("008"));

		//단골 카운팅
		model.addAttribute("dgCnt", bizMapper.BizDangolCnt());
		
		//리뷰 카운팅
		model.addAttribute("rwCnt", bizMapper.BizReviewCnt());

		//가게 리스트 이미지
		model.addAttribute("img", bizMapper.bizImgList());
		System.out.println("이미지=============="+bizMapper.bizImgList());

		return "rsvt/book01";
	}

	// 동네가게 상세정보(book0205)
	@RequestMapping("/goguma/bookmain/{bizNo}")

	public String bizInfo(@PathVariable String bizNo, Model model) {
		// 가게 정보(홈)
		model.addAttribute("biz", memService.bizInfo(bizNo));
		System.out.println("가게정보 ==========="+memService.bizInfo(bizNo));
		
		// 가게 소식
		model.addAttribute("news", newsService.bizNews(bizNo));
		// 가게 메뉴
		model.addAttribute("menu", menuService.bizMenu(bizNo));
		// 가게 리뷰
		model.addAttribute("rv", rvService.rsvtReview(bizNo));

		// 공통코드 시간
		model.addAttribute("code", codeService.codeList("007"));
		
		//단골 카운팅
		model.addAttribute("dgCnt", bizMapper.BizDangolCnt());
			
		//리뷰 카운팅
		model.addAttribute("rwCnt", bizMapper.BizReviewCnt());
		
		//가게 리스트
		model.addAttribute("img", bizMapper.bizImgList());
		
		//상세이미지
		model.addAttribute("imgDetail", memService.bizDetailImg(bizNo));
		System.out.println("이미지 상세정보============"+memService.bizDetailImg(bizNo));

		return "rsvt/book0205";
	}

	// 동네가게 등록(shop01)
	@PostMapping("/biz/shop1")
	public String bizInsert() {
		return "my/shop01";
	}
	// ==============================
	@GetMapping("/my/shop01")
	public String shop01() {
		return "biz/shop01";
	}

	@GetMapping("/biz/shop04")
	public String shop04() {
		return "biz/shop04";
	}
	
	//가게 후기리스트 페이지
	@GetMapping("/biz/shop05")
	public String shop05(HttpServletRequest request, Model model, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo) {
		//임시 세션(나중에 진짜 세션에서 불러오기)
		HttpSession session = request.getSession();
		session.setAttribute("bizNo", "bn001");
		String bizNo = (String) session.getAttribute("bizNo");
		System.out.println("로그인중인 유저의 가게번호 ===" + bizNo);
		System.out.println("dddd"+ rvService.selectReviewList(bizNo));
		
		model.addAttribute("rv",rvService.selectReviewList(bizNo));
		model.addAttribute("biz", memService.bizInfo(bizNo));
		
		//페이징
		pvo.setPageUnit(2);		//한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5);		//한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)
		
		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());
		
		pvo.setTotalRecord(rvService.selectReviewCnt(bizNo));
		
		return "biz/shop05";
	}

	@GetMapping("/biz/shop06")
	public String shop06(Model model, HttpServletRequest request, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo) {
		//임시 세션(나중에 진짜 세션에서 불러오기)
		HttpSession session = request.getSession();
		session.setAttribute("bizNo", "bn001");
		String bizNo = (String) session.getAttribute("bizNo");
		
		//페이징
		pvo.setPageUnit(3);		//한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5);		//한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)
		
		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());
		
		pvo.setTotalRecord(dangolService.selectShopDangolCnt(bizNo));
		
		model.addAttribute("cnt", dangolService.selectShopDangolCnt(bizNo));
		model.addAttribute("prd", dangolService.selectDangolPeriod(bizNo));
		model.addAttribute("dg", dangolService.selectDangolList(bizNo));
		return "biz/shop06";
	}

	@GetMapping("/biz/shop0602")
	public String shop0602() {
		return "biz/shop0602";
	}

	@GetMapping("/biz/shop07")
	public String shop07() {
		return "biz/shop07";
	}

	@GetMapping("/biz/shop0702")
	public String shop0702() {
		return "biz/shop0702";
	}

	@GetMapping("/biz/shop08")
	public String shop08() {
		return "biz/shop08";
	}
}
