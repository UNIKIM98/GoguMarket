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

	@Autowired
	BizMemService memService; // 가게정보
	@Autowired
	private BizNewsService newsService; // 가게소식 들고오기 위함
	@Autowired
	private BizMenuService menuService; // 메뉴 들고오기 위함
	@Autowired
	private RsvtRvService rvService; // 리뷰 들고오기 위함
	@Autowired
	BizMemMapper bizMapper; // 페이징 검색 하기 위함
	@Autowired
	CommonCodeService codeService; // 공통코드
	@Autowired
	BizDangolService dangolService; // 단골
	@Autowired
	RsvtService rsvtService;	//예약

//////////////////////▲Autowired▲//////////////////////
	
//	// 동네가게 예약 메인(book01).
//	@RequestMapping("/bookmain")
//	public String getBizList(Model model) {
//		model.addAttribute("lists", memService.getBizList());
//		return "rsvt/book01";
//	}

	// 동네가게 예약 메인 페이징
	@GetMapping("/goguma/bookmain")
	public String bizListPage(Model model, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo, BizMemVO vo) {
		pvo.setPageUnit(5); // 한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5); // 한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)

		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());

		pvo.setTotalRecord(bizMapper.bizListCnt(bvo));

		model.addAttribute("lists", bizMapper.bizListPage(bvo));

		// ＃ 가게 전체 셀렉트
		List<BizMemVO> lists = bizMapper.getBizList();

		// 카테고리 리스트
		model.addAttribute("ctgry", codeService.codeList("008"));

		// 단골 카운팅
		model.addAttribute("dgCnt", bizMapper.BizDangolCnt());

		// 리뷰 카운팅
		model.addAttribute("rwCnt", bizMapper.BizReviewCnt());

		// 가게 리스트 이미지
		model.addAttribute("img", bizMapper.bizImgList());
		System.out.println("이미지==============" + bizMapper.bizImgList());

		return "rsvt/book01";
	}
	
	//지도 ajax(마커 여러개)
	@GetMapping("/goguma/mapApiAjax")
	@ResponseBody
	public List<BizMemVO> mapApiAjax() {
		// DB에서 주소 목록을 가져오는 코드
	    List<BizMemVO> address = memService.getBizList();
		return address;
	}
	
	//지도 ajax(마커 한개)
	@GetMapping("/goguma/mapOneApiAjax")
	@ResponseBody
	public BizMemVO mapOneApiAjax(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");
		// DB에서 주소를 가져오는 코드
	    BizMemVO addressOne = memService.bizInfo(bizNo);
		return addressOne;
	}
	
	

	// 동네가게 상세정보(book0205)
	@RequestMapping("/goguma/bookmain/{bizNo}")

	public String bizInfo(@PathVariable String bizNo, Model model) {
		// 가게 정보(홈)
		model.addAttribute("biz", memService.bizInfo(bizNo));
		System.out.println("가게정보 ===========" + memService.bizInfo(bizNo));

		// 가게 소식
		model.addAttribute("news", newsService.selectBizNews(bizNo));
		// 가게 메뉴
		model.addAttribute("menu", menuService.bizMenu(bizNo));
		// 가게 리뷰
		model.addAttribute("rv", rvService.rsvtReview(bizNo));

		// 공통코드 시간
		model.addAttribute("code", codeService.codeList("007"));

		// 단골 카운팅
		model.addAttribute("dgCnt", bizMapper.BizDangolCnt());

		// 리뷰 카운팅
		model.addAttribute("rwCnt", bizMapper.BizReviewCnt());

		// 가게 리스트
		model.addAttribute("img", bizMapper.bizImgList());

		// 상세이미지
		model.addAttribute("imgDetail", memService.bizDetailImg(bizNo));
		System.out.println("이미지 상세정보============" + memService.bizDetailImg(bizNo));

		return "rsvt/book0205";
	}

///////////////////////////////////////////▲지영▲///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////▼효근▼///////////////////////////////////////////
	// 동네가게 등록(shop01)
	@GetMapping("/my/shop01")
	public String shop01() {
		return "/biz/shop01";
	}

	// ==============================
	@PostMapping("/my/bizInsert")
	public String bizInsert(BizMemVO vo, HttpSession session) {
		String result = "fail";
		int cnt = memService.bizInsert(vo,session);

		
		
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
		
		model.addAttribute("bizMem",vo);
		///////////////////

		System.out.println("=====session====="+session);
		System.out.println("=====vo====="+vo);
		
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
	public Map<String,Object> selectBizNewsAjax(HttpServletRequest request, Model model) {

		
		Map<String,Object> news = new HashMap<String, Object>();
		HttpSession session = request.getSession();				//세션받아옴
		String bizNo = (String) session.getAttribute("bizNo");	//세션에서 bizNo값 받아옴
		
		List<BizNewsVO> bizNews = newsService.selectBizNews(bizNo);	//세션에서 받아온bizNo로 bizNews매퍼 돌림
		
		news.put("news",bizNews);		//위에서 돌린 List값을 모델에 담아주고 "news"라고함.
		
		System.out.println(bizNews);
		System.out.println(model);
		return news;
	}
///////////////////////////////////////////▲효근▲///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////▼지영▼///////////////////////////////////////////	
	@GetMapping("/biz/shop04")
	public String shop04() {
		return "biz/shop04";
	}
	
	//가게 후기리스트 페이지
	@GetMapping("/biz/reviewList")
	public String shop05(HttpServletRequest request, Model model, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo, RsvtRvVO rvo) {
		//임시 세션(나중에 진짜 세션에서 불러오기)

		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");
		
		System.out.println("로그인중인 유저의 가게번호 ===" + bizNo);
		System.out.println("dddd" + rvService.selectReviewList(bizNo));

		model.addAttribute("rv", rvService.selectReviewList(bizNo));
		model.addAttribute("biz", memService.bizInfo(bizNo));

		// 페이징
		pvo.setPageUnit(2); // 한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5); // 한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)


		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());

		pvo.setTotalRecord(rvService.selectReviewCnt(bizNo));

		return "biz/shop05";
	}


	@GetMapping("/biz/dangolList")
	public String shop06(Model model, HttpServletRequest request, @ModelAttribute("bobo") BizSearchVO bvo, PagingVO pvo) {
		//임시 세션(나중에 진짜 세션에서 불러오기)

		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");

		// 페이징
		pvo.setPageUnit(3); // 한페이지에 몇건씩 보여줄건지
		pvo.setPageSize(5); // 한페이지에 몇페이지씩 보여줄건지(밑에 페이지 수)

		bvo.setFirst(pvo.getFirst());
		bvo.setLast(pvo.getLast());

		pvo.setTotalRecord(dangolService.selectShopDangolCnt(bizNo));

		model.addAttribute("cnt", dangolService.selectShopDangolCnt(bizNo));
		model.addAttribute("prd", dangolService.selectDangolPeriod(bizNo));
		model.addAttribute("dg", dangolService.selectDangolList(bizNo));
		model.addAttribute("biz", memService.bizInfo(bizNo));
		return "biz/shop06";
	}

	@GetMapping("/biz/dangolDetailPage/{userId}")
	public String shop0602(@PathVariable String userId, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");
		
		List<BizDangolVO> dangols = dangolService.selectDangolPersonal(userId);
		model.addAttribute("dangol", dangols.get(0));
		model.addAttribute("dgInfo", dangols);
		model.addAttribute("biz", memService.bizInfo(bizNo));
		List<RsvtRvVO> userReview = rvService.selectReviewUser(userId);
		model.addAttribute("userRv", userReview);
		System.out.println("리뷰정보불러오나?===="+userReview);
		System.out.println(dangols);
		return "biz/shop0602";
	}

	@GetMapping("/biz/Certification")
	public String shop07(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");
		model.addAttribute("biz", memService.bizInfo(bizNo));
		return "biz/shop07";
	}

	@GetMapping("/biz/shop0702")
	public String shop0702() {
		return "biz/shop0702";
	}
	
	//비즈 예약관리 페이지
	@GetMapping("/biz/bizRsvtApprove")
	public String shop08(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");
		System.out.println("shop08" + bizNo);
		
		System.out.println(rsvtService.selectBizRsvt(bizNo)); 
		model.addAttribute("lists", rsvtService.selectBizRsvt(bizNo));
		model.addAttribute("code", codeService.codeList("007"));
		model.addAttribute("biz", memService.bizInfo(bizNo));
		return "biz/shop08";
	}
  
		// 예약내역 업데이트
	@GetMapping("/biz/updateRsvtAjax/{rsvtNo}")
	@ResponseBody
	public String updateRsvtAjax(@PathVariable String rsvtNo) {
		System.out.println("=====" + rsvtService.updateRsvtInfo(rsvtNo));

		// rsvt_update 테이블 -> rsvt 테이블 업데이트
		rsvtService.updateRsvtInfo(rsvtNo);

		// rsvt_update 테이블 승인여부 N -> Y
		rsvtService.updateApprove(rsvtNo);

		// rsvt테이블 상태변경 (->예약확정);
		rsvtService.updateRsvtSttsCompl(rsvtNo);

		return rsvtNo;

	}
	
	//비즈 메인으로 이동(현재 : 마이페이지에서 목록 선택시 이동)
	@GetMapping("/biz/bizMain")
	public String bizMain(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String bizNo = (String) session.getAttribute("bizNo");
		
		model.addAttribute("biz", memService.bizInfo(bizNo));			
		
		// 공통코드 시간
		model.addAttribute("code", codeService.codeList("007"));
		model.addAttribute("imgDetail", memService.bizDetailImg(bizNo));
		model.addAttribute("news", newsService.selectBizNews(bizNo));
		return "biz/shopMain";
	}

}
