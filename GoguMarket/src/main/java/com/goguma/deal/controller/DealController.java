package com.goguma.deal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.service.AtchService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.service.SearchService;
import com.goguma.common.service.TestService;
import com.goguma.common.vo.AtchVO;
import com.goguma.common.vo.SearchVO;
import com.goguma.deal.service.DealReviewService;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealReviewVO;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;
import com.goguma.deal.vo.Paging;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@Controller

public class DealController {

	@Autowired
	private DealService dealService;

	@Autowired
	private DealReviewService RvService;

	@Autowired
	private AtchService atchService;

	@Autowired
	private CommonCodeService codeService;

	@Autowired
	private SearchService searchService;

	@Autowired
	TestService testService;

	@Autowired
	MemService memService;

// ❤ 온니 안 쓰는 거 같아서 일단 막아뒀어요
//	// 판매상품 등록 : 계좌정보와 아이디값이 없으면 등록할 수 없다 => @PostMapping("/deal/{acntno}/{id}")
//	// 일단 폼데이타로 부메랑에서 들어가는지 확인해보자
//	@PostMapping("/deal")
//	@ResponseBody
//	public DealVO insertDeal(DealVO vo, MultipartFile file) {
//		return vo;
//	}

//판매자 페이지에 있던 것
//❤❤ 언니 요거 안 써서 일단 주석해뒀어욤
//	model.addAttribute("deal", dealService.selectDeal(dlNo)); // 단건
//	model.addAttribute("list", dealService.getDealSeller(dlNo));// 여러건

//	int id = Integer.parseInt(dealService.getId(dlNo));
//	model.addAttribute("file", atchService.selectAtch(id));

	// ===========================
	// ▷ 중고거래 메인
	@RequestMapping("/goguma/dealMain") // 중고거래 메인 페이지
	public String dealMain(Paging paging, Model model, SearchVO scvo, @ModelAttribute("dsvo") DealSearchVO svo) {
		paging.setPageUnit(8); // 한 페이지에 출력할 글 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(dealService.getcountTotal(svo));

		model.addAttribute("lists", dealService.dealListSelect(svo));
		model.addAttribute("category", codeService.codeList("002")); // string 공통코드 넣으면 모든테이블이나옴 저기서 나는
																		// common_detail_code만 들고오면됨

		model.addAttribute("word", searchService.getPopularWord());

		searchService.insertSearch(scvo); // 검색어저장ㅅㅂ
		System.out.println(searchService.getPopularWord() + "wkfs나오낫");
		return "deal/dealMain";
	}

	// ===========================
	// ▷ 판매중 전체조회
	@RequestMapping("/goguma/dealList") // 판매상품 전체 조회
	public String dealListSelect(Model model, @ModelAttribute("dsvo") DealSearchVO svo, Paging paging) {

		paging.setPageUnit(5); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		paging.setTotalRecord(dealService.getcountTotal(svo));

		model.addAttribute("lists", dealService.dealListSelect(svo));
		model.addAttribute("category", codeService.codeList("002")); // string 공통코드 넣으면 모든테이블이나옴 저기서 나는
																		// common_detail_code만 들고오면됨
		return "deal/dealList"; // 뷰페이지명
	}

	// ===========================
	// ▷ 판매상품 단건조회
	@RequestMapping("/goguma/dealdetail/{dlNo}")
	public String getDeal(@PathVariable int dlNo, Model model) {

		System.out.println("단건조회 =====> " + dlNo);
		model.addAttribute("deal", dealService.selectDeal(dlNo));
		model.addAttribute("list", dealService.getDealSeller(dlNo));// 판매자의 다른 상품들
		model.addAttribute("ct", dealService.getDealCtgry(dlNo)); // 카테고리
		model.addAttribute("file", dealService.selectDealAtch(dlNo)); // 해당게시글 첨부파일들
		model.addAttribute("cnt", dealService.dealHitUpdate(dlNo));// 조회수

		return "deal/dealdetail";
	}

	// ===========================
	// ▷ 중고거래 게시글 작성
	@RequestMapping("/my/dealform") // 딜폼창확인
	public String dealform(Model model) {
		model.addAttribute("category", codeService.codeList("002"));

		return "deal/dealform";
	}
	
	// ===========================
	// ▷ 중고거래 게시글 작성 submit
	@RequestMapping("/my/dealformsubmit") // 딜폼창확인
	@ResponseBody
	public String dealform(DealVO vo, List<MultipartFile> files) {
		/* System.out.println(vo.getAtchId() + "메롱"); */
		int atchId = atchService.insertFile(files);

		if (atchId > 0) {
			vo.setAtchId(atchId);
		}
		dealService.insertDeal(vo);
		return "redirect:dealList";
	}

	// ▷ 중고거래 리뷰 작성 폼 : 단건 게시글에 대한 리뷰
	@RequestMapping("/my/dealReview/{dlNo}")
	public String dealReview(Model model,@PathVariable int dlNo) {
		model.addAttribute("deal", dealService.selectDeal(dlNo)); 
		
		return "deal/dealReview";
	}
	// ▷ 중고거래 리뷰 작성 submit
	@RequestMapping("/my/dealReviewsubmit")
	@ResponseBody
	public String dealReview(DealReviewVO rvo, List<MultipartFile> files){
		int atchId = atchService.insertFile(files);

		if (atchId > 0) {
			rvo.setAtchId(atchId);
		}
		//후기입력
		RvService.insertDealRv(rvo);
		return "redirect:/goguma/dealdetail/"+rvo.getDlNo(); // 이전페이지로 가고싶은데 dlNo 유지한채로되나? 
	}

	// ===========================
	// ▷ 중고거래 게시글 수정
	@GetMapping("/my/dealupdate/{dlNo}")
	public String updateTest(Model model, HttpServletRequest request, @PathVariable int dlNo) {
		// 임시로그인 : 세션에 아이디, 거래지역 담기
		HttpSession session = request.getSession();  
		MemVO mVO = new MemVO();

		mVO.setUserId((String) session.getAttribute("userId"));
		mVO = memService.selectUser(mVO);

		// 게시글 정보 담기
		model.addAttribute("dealInfo", testService.selectDealTest(dlNo)); // 7번 게시글 정보

		model.addAttribute("atchList", testService.selectDealAtchTest(dlNo)); // 7번 게시글 첨부파일 목록

		model.addAttribute("category", codeService.codeList("002")); // 카테고리 정보

		return "deal/dealUpdateForm";
	}

	// ===========================
	// ▷ 중고거래 게시글 삭제
	@RequestMapping("/my/deleteDeal/{dlNo}")
	public void deleteDeal(@PathVariable int dlNo, HttpServletResponse response) {
		System.out.println(dlNo + " => 삭제할 글 번호");

		DealVO dVO = testService.selectDealTest(dlNo);
		System.out.println(dVO + " => 삭제할 글 정보");

		List<AtchVO> atchList = testService.selectDealAtchTest(dlNo);
		System.out.println(atchList + " => 삭제할 첨부파일들 정보");

		int delDeal = testService.deleteDealTest(dVO);
		System.out.println("게시글 삭제했으면 1 => " + delDeal);

		int delAtch = atchService.deleteFile(atchList);
		System.out.println("첨부파일 삭제했으면 1 이상 => " + delAtch);

		try {
			if (delDeal > 0) {
				System.out.println("왔니..delAcut 안쪽임");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script language='javascript'>");
				out.println("alert('[삭제완료] 게시글 삭제가 정상적으로 완료되었습니다. :D '); location.href='/goguma/dealMain';");

				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('[삭제실패] 게시글 삭제 중 오류가 발생하였습니다 :( '); location.href='/goguma/dealMain';");
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* return Collections.singletonMap("result", "success delete"); */
	}

	
	
	// ===========================
	// ❤❤ 판매자 페이지(로 추정)
	@RequestMapping("/goguma/dealSellerpage/{ntslId}")
	public String getDealSeller(@PathVariable String ntslId, Model model) {

		System.out.println("왔슈...." + ntslId);
		model.addAttribute("dealList", dealService.selectNtslDeal(ntslId));
		System.out.println(dealService.selectNtslDeal(ntslId));

		// 판매자 후기 정보
		// ❤❤ 넣어야함!!!
		model.addAttribute("review", RvService.getDealRv(ntslId));// 여러건 . 후기 조회

		return "deal/dealSellerPage";
	}
	

}
