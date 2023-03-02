package com.goguma.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.biz.mapper.BizMemMapper;
import com.goguma.biz.service.BizDangolService;
import com.goguma.biz.service.BizMemService;
import com.goguma.biz.service.BizNewsService;
import com.goguma.biz.vo.BizDangolVO;
import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizSearchVO;
import com.goguma.biz.vo.PagingVO;
import com.goguma.common.service.AtchService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.rsvt.service.BizMenuService;
import com.goguma.rsvt.service.RsvtRvService;

@Controller
public class BizController {

	@Autowired
	BizMemService memService; // 가게정보
	@Autowired
	BizNewsService newsService; // 가게소식 들고오기 위함
	@Autowired
	BizMenuService menuService; // 메뉴 들고오기 위함
	@Autowired
	RsvtRvService rvService; // 리뷰 들고오기 위함
//	@Autowired BizMemMapper bizMapper;				//페이징 검색 하기 위함
	@Autowired
	CommonCodeService codeService; // 공통코드
	@Autowired
	BizDangolService dgService;

	/*
	 * book01~05 분류는 예약으로 되어있는데 매퍼랑 서비스 같은게 biz에 있어서 여기에다가 만듬
	 */

//	// 동네가게 예약 메인(book01).
//	@RequestMapping("/bookmain")
//	public String getBizList(Model model) {
//		model.addAttribute("lists", memService.getBizList());
//		return "rsvt/book01";
//	}

	// 동네가게 예약 메인 페이징
	@GetMapping("/bookmain")
	public String bizListPage(Model model, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo) {
		pvo.setPageUnit(2); // 한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5); // 한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)

		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());

		pvo.setTotalRecord(memService.bizListCnt(bvo));

		model.addAttribute("lists", memService.bizListPage(bvo));
		System.out.println("페이징test●●●●●●●●●●●●●●●●●●●●●" + memService.bizListPage(bvo));

		// ＃ 전체 가게 리스트 셀렉트 : ✔️위에 lists 있으면 필요 없는 건가효?!
		List<BizMemVO> mlist = memService.getBizList();
		model.addAttribute("mlist", mlist);
		

		// # 전체 가게 단골수+bizNo 셀렉트
		List<BizDangolVO> dgList = dgService.countDangol();
		System.out.println("단골수"+dgList);
		model.addAttribute("dgList", dgList);

		return "rsvt/book01";
	}

	// 사진(ajax..?)
	@GetMapping("/bizListImg")
	public List<BizMemVO> getBizListImg(Model model) {
		List<BizMemVO> result = memService.bizMemImg();
		model.addAttribute("img", result.get(0).getAtchPath());
		System.out.println("사진 결과 000000000" + result.get(0).getAtchPath());
		return result;
	}

	// 동네가게 상세정보(book0205)
	@RequestMapping("/book0205/{bizNo}")
	public String bizInfo(@PathVariable String bizNo, Model model) {
		// 가게 정보(홈)
		model.addAttribute("biz", memService.bizInfo(bizNo));
		// 가게 소식
		model.addAttribute("news", newsService.bizNews(bizNo));
		// 가게 메뉴
		model.addAttribute("menu", menuService.bizMenu(bizNo));
		// 가게 리뷰
		model.addAttribute("rv", rvService.rsvtReview(bizNo));

		// 공통코드 시간
		model.addAttribute("code", codeService.codeList("007"));
		System.out.println("공통코드●●●●●●●●●●●●●●●●●●●●●" + codeService.codeList("007"));

		return "rsvt/book0205";
	}

	// ==============================

	@GetMapping("/shop04")
	public String shop04() {
		return "biz/shop04";
	}

	@GetMapping("/shop05")
	public String shop05() {
		return "biz/shop05";
	}

	@GetMapping("/shop06")
	public String shop06() {
		return "biz/shop06";
	}

	@GetMapping("/shop0602")
	public String shop0602() {
		return "biz/shop0602";
	}

	@GetMapping("/shop07")
	public String shop07() {
		return "biz/shop07";
	}

	@GetMapping("/shop0702")
	public String shop0702() {
		return "biz/shop0702";
	}

	@GetMapping("/shop08")
	public String shop08() {
		return "biz/shop08";
	}
}
