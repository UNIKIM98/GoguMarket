package com.goguma.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.biz.mapper.BizMemMapper;
import com.goguma.biz.service.BizDangolService;
import com.goguma.biz.service.BizMemService;
import com.goguma.biz.service.BizNewsService;
import com.goguma.biz.vo.BizDangolVO;
import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizNewsVO;
import com.goguma.biz.vo.BizSearchVO;
import com.goguma.biz.vo.PagingVO;
import com.goguma.common.service.CommonCodeService;
import com.goguma.rsvt.service.BizMenuService;
import com.goguma.rsvt.service.RsvtRvService;
import com.goguma.rsvt.service.RsvtService;
import com.goguma.rsvt.vo.RsvtRvVO;

@Controller
public class BizController {

	@Autowired BizMemService memService; 			// 가게정보
	@Autowired private BizNewsService newsService;	// 가게소식 들고오기 위함
	@Autowired private BizMenuService menuService;	// 메뉴 들고오기 위함
	@Autowired private RsvtRvService rvService; 	// 리뷰 들고오기 위함
	@Autowired BizMemMapper bizMapper; 				// 페이징 검색 하기 위함
	@Autowired CommonCodeService codeService; 		// 공통코드
	@Autowired BizDangolService dangolService; 		// 단골
	@Autowired RsvtService rsvtService; 			// 예약

//////////////////////▲Autowired▲//////////////////////

	// 동네가게 예약 메인 페이징
	@GetMapping("/goguma/bookmain")
	public String bizListPage(Model model, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo, BizMemVO vo) {
		pvo.setPageUnit(5); 		// 한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5); 		// 한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)

		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());

		pvo.setTotalRecord(bizMapper.bizListCnt(bvo));
		model.addAttribute("lists", bizMapper.bizListPage(bvo));

		List<BizMemVO> lists = bizMapper.getBizList();				// ＃ 가게 전체 셀렉트

		model.addAttribute("ctgry", codeService.codeList("008"));	// 카테고리 리스트
		model.addAttribute("dgCnt", bizMapper.BizDangolCnt());		// 단골 카운팅
		model.addAttribute("rwCnt", bizMapper.BizReviewCnt());		// 리뷰 카운팅
		model.addAttribute("img", bizMapper.bizImgList());			// 가게 리스트 이미지

		return "rsvt/book01";
	}

	// 지도 ajax(마커 여러개)
	@GetMapping("/goguma/mapApiAjax")
	@ResponseBody
	public List<BizMemVO> mapApiAjax() {
		List<BizMemVO> address = memService.getBizList();		// DB에서 List로 가져옴
		
		return address;
	}

	// 지도 ajax(마커 한개)
	@GetMapping("/goguma/mapOneApiAjax")
	@ResponseBody
	public BizMemVO mapOneApiAjax(HttpServletRequest request) {
		HttpSession session = request.getSession();				//session에서 정보 가져오기 
		String bizNo = (String) session.getAttribute("bizNo");
		
		BizMemVO addressOne = memService.bizInfo(bizNo);		// DB에서 List로 가져옴
		
		return addressOne;
	}

	// 동네가게 상세정보(book0205)
	@RequestMapping("/goguma/bookmain/{bizNo}")
	public String bizInfo(@PathVariable String bizNo, Model model) {
		model.addAttribute("biz", memService.bizInfo(bizNo));				// 가게 정보(홈)
		model.addAttribute("news", newsService.selectBizNews(bizNo));		// 가게 소식
		model.addAttribute("menu", menuService.bizMenu(bizNo));				// 가게 메뉴
		model.addAttribute("rv", rvService.rsvtReview(bizNo));				// 가게 리뷰
		model.addAttribute("code", codeService.codeList("007"));			// 공통코드 시간
		model.addAttribute("dgCnt", bizMapper.BizDangolCnt());				// 단골 카운팅
		model.addAttribute("rwCnt", bizMapper.BizReviewCnt());				// 리뷰 카운팅
		model.addAttribute("img", bizMapper.bizImgList());					// 가게 리스트
		model.addAttribute("imgDetail", memService.bizDetailImg(bizNo));	// 상세이미지

		return "rsvt/book0205";
	}

///////////////////////////////////////////▲지영▲///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////▼효근▼///////////////////////////////////////////
	// 동네가게 등록(shop01)
	@GetMapping("/my/shop01")
	public String shop01(Model model, HttpSession session) {
		String bizNo = (String) session.getAttribute("bizNo");
		model.addAttribute("biz", memService.bizInfo(bizNo));
		return "biz/shop01";
	}

	// ==============================
	@PostMapping("/my/bizInsert")
	public String bizInsert(BizMemVO vo, HttpSession session) {
		String result = "fail";
		int cnt = memService.bizInsert(vo, session);

		if (cnt > 0) {
			vo.setBizNo(result);
			result = "success";
		}
		return "redirect:/goguma/bookmain";
	}

	// 동네가게 프로필 단건조회
	@GetMapping("/biz/shop02")
	public String shop02(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");

		BizMemVO vo = new BizMemVO();
		vo.setBizNo(bizNo);
		vo = memService.bizInfo(bizNo);

		model.addAttribute("bizMem", vo);
		///////////////////

		System.out.println("=====session=====" + session);
		System.out.println("=====vo=====" + vo);

		return "biz/shop02";
	}

	@PostMapping("/biz/shopUpdateSubmit")
	@ResponseBody
	public String shopUpdateSubmit(BizMemVO vo) {
		System.out.println(vo);
		return null;
	}

	// 동네가게 수정하기
	@GetMapping("/biz/shop03")
	public String shop03(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");

		BizMemVO vo = new BizMemVO();
		vo.setBizNo(bizNo);

		vo = memService.updateBizMem(bizNo);
		model.addAttribute("updateBizMem", vo);
//		model.addAttribute("update",memService.updateBizMem(bizNo));

		return "biz/shop02";
	}

	@GetMapping("/biz/selectBizNewsAjax")
	@ResponseBody
	public Map<String, Object> selectBizNewsAjax(HttpServletRequest request, Model model) {

		Map<String, Object> news = new HashMap<String, Object>();
		HttpSession session = request.getSession(); // 세션받아옴
		String bizNo = (String) session.getAttribute("bizNo"); // 세션에서 bizNo값 받아옴

		List<BizNewsVO> bizNews = newsService.selectBizNews(bizNo); // 세션에서 받아온bizNo로 bizNews매퍼 돌림

		news.put("news", bizNews); // 위에서 돌린 List값을 모델에 담아주고 "news"라고함.

		System.out.println(bizNews);
		System.out.println(model);
		return news;
	}

///////////////////////////////////////////▲효근▲///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////▼지영▼///////////////////////////////////////////	

	// 가게 후기리스트 페이지
	@GetMapping("/biz/reviewList")
	public String shop05(HttpServletRequest request, Model model, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo,
			RsvtRvVO rvo) {
		HttpSession session = request.getSession();						//세션에서 정보 가져오기
		String bizNo = (String) session.getAttribute("bizNo");

		model.addAttribute("rv", rvService.selectReviewList(bizNo));	// 리뷰 목록
		model.addAttribute("biz", memService.bizInfo(bizNo));			// 가게 정보

		// 페이징
		pvo.setPageUnit(5); // 한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5); // 한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)

		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());

		pvo.setTotalRecord(rvService.selectReviewCnt(bizNo));

		return "biz/shop05";
	}
	
	//단골페이지 출력
	@GetMapping("/biz/dangolList")
	public String shop06(Model model, HttpServletRequest request, @ModelAttribute("bobo") BizSearchVO bvo,
			PagingVO pvo) {
		HttpSession session = request.getSession();				//세션에서 정보 가져오기
		String bizNo = (String) session.getAttribute("bizNo");

		// 페이징
		pvo.setPageUnit(3); // 한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5); // 한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)

		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());

		pvo.setTotalRecord(dangolService.selectShopDangolCnt(bizNo));

		model.addAttribute("cnt", dangolService.selectShopDangolCnt(bizNo));	// 단골 수 카운팅
		model.addAttribute("prd", dangolService.selectDangolPeriod(bizNo));		// 단골 집계 기간
		model.addAttribute("dg", dangolService.selectDangolList(bizNo));		// 단골 리스트 출력
		model.addAttribute("biz", memService.bizInfo(bizNo));					// 가게 정보
		
		return "biz/shop06";
	}
	
	//단골 상세페이지
	@GetMapping("/biz/dangolDetailPage/{userId}")
	public String shop0602(@PathVariable String userId, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();				//세션에서 정보 가져오기
		String bizNo = (String) session.getAttribute("bizNo");

		List<BizDangolVO> dangols = dangolService.selectDangolPersonal(userId);		//1명의 여러건 정보 list에 담기
		model.addAttribute("dangol", dangols.get(0));
		model.addAttribute("dgInfo", dangols);
		model.addAttribute("biz", memService.bizInfo(bizNo));
		
		List<RsvtRvVO> userReview = rvService.selectReviewUser(userId);
		model.addAttribute("userRv", userReview);
		
		return "biz/shop0602";
	}
	
	//사업자인증 페이지
	@GetMapping("/biz/Certification")
	public String shop07(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();				//세션에서 정보 가져오기
		String bizNo = (String) session.getAttribute("bizNo");
		
		model.addAttribute("biz", memService.bizInfo(bizNo));
		
		return "biz/shop07";
	}

	// 비즈 예약관리 페이지
	@GetMapping("/biz/bizRsvtApprove")
	public String shop08(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();						//세션에서 정보 가져오기
		String bizNo = (String) session.getAttribute("bizNo");

		model.addAttribute("lists", rsvtService.selectBizRsvt(bizNo));	// 예약리스트 출력
		model.addAttribute("code", codeService.codeList("007"));		// 공통코드
		model.addAttribute("biz", memService.bizInfo(bizNo));			// 가게정보
		
		return "biz/shop08";		
	}

	// 예약내역 업데이트
	@GetMapping("/biz/updateRsvtAjax/{rsvtNo}")
	@ResponseBody
	public String updateRsvtAjax(@PathVariable String rsvtNo) {
		rsvtService.updateRsvtInfo(rsvtNo);			// rsvt_update 테이블 -> rsvt 테이블 업데이트
		rsvtService.updateApprove(rsvtNo);			// rsvt_update 테이블 승인여부 N -> Y
		rsvtService.updateRsvtSttsCompl(rsvtNo);	// rsvt테이블 상태변경 (->예약확정);

		return rsvtNo;
	}

	// 비즈 메인으로 이동(현재 : 마이페이지에서 목록 선택시 이동)
	@GetMapping("/biz/bizMain")
	public String bizMain(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();						// 세션에서 정보 가져오기
		String bizNo = (String) session.getAttribute("bizNo");
		
		model.addAttribute("biz", memService.bizInfo(bizNo));				// 가게정보
		model.addAttribute("code", codeService.codeList("007"));			// 공통코드 시간
		model.addAttribute("imgDetail", memService.bizDetailImg(bizNo));	// 개별 이미지 불러오기
		model.addAttribute("news", newsService.selectBizNews(bizNo));		// 가게 소식
		
		return "biz/shopMain";
	}
	
	//가게 정보 수정
	@GetMapping("/biz/bizModify")
	public String bizModify(Model model, HttpSession session) {
		String bizNo = (String) session.getAttribute("bizNo");
		model.addAttribute("biz", memService.bizInfo(bizNo));
		return "biz/bizModify";
	}
}
