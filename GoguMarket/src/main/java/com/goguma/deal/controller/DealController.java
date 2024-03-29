package com.goguma.deal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
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
import com.goguma.deal.service.DealRvVoteService;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealReviewVO;
import com.goguma.deal.vo.DealRvSearchVO;
import com.goguma.deal.vo.DealRvVoteVO;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;
import com.goguma.deal.vo.Paging;
import com.goguma.mem.service.MemService;
import com.goguma.mem.service.PointService;
import com.goguma.mem.vo.MemVO;
import com.goguma.mem.vo.PointVO;

@Controller

public class DealController {

	@Autowired
	private DealService dealService;

	@Autowired
	private DealReviewService rvService;

	@Autowired
	private DealRvVoteService voteService;

	@Autowired
	private AtchService atchService;

	@Autowired
	private CommonCodeService codeService;

	@Autowired
	private SearchService searchService; // 검색어

	@Autowired 
	private PointService pService;	// 포인트

	@Autowired
	TestService testService;

	@Autowired
	MemService memService;

	/////////////////// [[ 1. 중고마켓 ]]ㅇ
	// ===========================
	// ▷ 중고마켓 메인페이지
	@RequestMapping("/goguma/dealMain") // 중고거래 메인 페이지 : 판매중인 물건만 보임
	public String dealMain(Paging paging, Model model, SearchVO scvo, @ModelAttribute("dsvo") DealSearchVO svo) {
		// 판매중인 데이터만만 보이도록
		paging.setPageUnit(8); // 한 페이지에 출력할 글 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		svo.setStts("판매중"); 
		svo.setFirst(paging.getFirst()); // 첫번째글 
		svo.setLast(paging.getLast());	 // 마지막글 
		paging.setTotalRecord(dealService.getcountTotal(svo)); // 전체 레코드 건수
		model.addAttribute("lists", dealService.dealListSelect(svo)); // 전체건수를 담을 모델

		// 검색어저장시 null값이 아닌 값만 들어가도록 한다
		if (scvo.getSearchTtl() != null && !scvo.getSearchTtl().equals("")) {
			scvo.setPstSe("중고거래"); // 
			scvo.setStts("1"); // 처음 들어갈때 상태값은 1
			searchService.insertSearch(scvo);
		} 
		
		model.addAttribute("category", codeService.codeList("002")); // 공통코드 002(중고거래) 카테고리를 담을 모델
		model.addAttribute("word", searchService.getPopularWord()); // 인기검색어가 담길 모델 = 관리자페이지에서 stts="2"인 아이들만 출력이된다.
		return "deal/dealMain";
	}

	// ===========================
	// ▷ 중고마켓 게시글 작성
	@RequestMapping("/my/dealform") // 딜폼창확인
	public String dealform(Model model) {
		model.addAttribute("category", codeService.codeList("002")); // 게시글 작성시, 카테고리 공통코드 이름에 사용할 모델사용

		return "deal/dealform";
	}

	// ===========================
	// ▷ 중고마켓 게시글 작성 submit
	@RequestMapping("/my/dealformsubmit") 
	@ResponseBody
	public String dealform( HttpSession session, 
			                String userId, 
			                DealVO vo, 
			                List<MultipartFile> files) {
		userId = (String) session.getAttribute("userId"); // 현재 로그인한 세션값으로 판매글을 등록할것이다

		int atchId = atchService.insertFile(files);		  // 첨부파일

		if (atchId > 0) {
			vo.setAtchId(atchId);
		}
		dealService.insertDeal(vo, userId); // pointVO에 userId담아주기 위해 사용
		return vo.getDlTtl(); 				// 작성후에 타이틀명 보이도록 리턴값으로 받아주기
	}

	// ===========================
	// ▷ 중고마켓 판매상품 단건조회
	@RequestMapping("/goguma/dealdetail/{dlNo}")
	public String getDeal(@PathVariable int dlNo, Model model, Paging paging, DealSearchVO svo) {
		
		DealVO vo = dealService.selectDeal(dlNo);	// 판매자아이디를 구하기위해  단건조회

		// 단건조회 내의 탭페이지에서 판매자의 다른 모든 판매 상품 목록들을 페이징
		paging.setPageUnit(3); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		svo.setNtslId(vo.getNtslId()); // 현재 판매자의 아이디를 넣어주기
		paging.setTotalRecord(dealService.getcountTotal(svo)); // 현재 dlno를 이용한 ntsl_id가 가진 총 데이터건수
		model.addAttribute("list", dealService.getDealSeller(svo));		// 판매자의 다른 상품들
		
		
		model.addAttribute("profile", dealService.selectNtslDeal(svo)); // 판매자 정보를 보여줄 모델
		model.addAttribute("category", codeService.codeList("002")); 	// string 공통코드 넣으면 모든테이블이나옴 저기서 나는
		model.addAttribute("deal", vo);									// 상품정보
		model.addAttribute("ct", dealService.getDealCtgry(dlNo)); 		// 카테고리
		model.addAttribute("file", dealService.selectDealAtch(dlNo)); 	// 해당게시글 첨부파일들
		model.addAttribute("cnt", dealService.dealHitUpdate(dlNo));		// 조회수
		
		// 시세를 담는 모델 : 새로운 생성자를 만들어서 제목을 담고 시세를 조회한다.
		DealSearchVO dsvo = new DealSearchVO();
		dsvo.setDlTtl(vo.getDlTtl());
		model.addAttribute("prc", dealService.selectPrice(dsvo));	

		return "deal/dealdetail";
	}

	// ===========================
	// ▷ 중고거래 리뷰 작성 폼 : 단건 게시글에 대한 리뷰

	@RequestMapping("/my/dealReview/{dlNo}")
	public String dealReview(Model model, @PathVariable int dlNo) {
		MemVO mVO = new MemVO();
		mVO.setUserId(dealService.selectDeal(dlNo).getNtslId());
		model.addAttribute("deal", dealService.selectDeal(dlNo));
		model.addAttribute("ntslInfo", memService.selectUser(mVO));
		return "deal/dealReview";
	}

	// ===========================
	// ▷ 중고거래 리뷰 작성 submit
	@RequestMapping("/my/dealReviewsubmit")
	@ResponseBody
	public int dealReview(PointVO pvo, 
			              DealReviewVO rvo, 
			              DealRvVoteVO vtList, 
			              String userId,
			              HttpSession session) {

		userId = (String) session.getAttribute("userId");
		// 투표 !, 리뷰 인서트
		int rvNo = rvService.insertDealRv(rvo, vtList.getVtList(), userId);

		return rvo.getDlNo();
	}

	// ===========================
	// 중고거래 구매예약폼
	@GetMapping("/my/dealUpdateRsvt/{dlNo}")
	public String updateRsvt(Model model, HttpServletRequest request, @PathVariable int dlNo) {
		// 구매자 정보
		HttpSession session = request.getSession();
		MemVO mVO = new MemVO();

		mVO.setUserId((String) session.getAttribute("userId"));
		mVO = memService.selectUser(mVO);

		// 판매자정보
		MemVO sellerVO = new MemVO();
		sellerVO.setUserId(dealService.selectDeal(dlNo).getNtslId());
		model.addAttribute("ntslInfo", memService.selectUser(sellerVO));

		// 게시글 정보 담기
		model.addAttribute("dealInfo", testService.selectDealTest(dlNo)); // 7번 게시글 정보
		model.addAttribute("atchList", testService.selectDealAtchTest(dlNo)); // 7번 게시글 첨부파일 목록
		model.addAttribute("category", codeService.codeList("002")); // 카테고리 정보
		return "deal/dealUpdateRsvt";
	}

	// 구매완료 : ❤❤ 판매완료
	@PostMapping("/my/dealUpdateSubmit")
	public void dealUpdateSubmit(DealVO dVO, HttpServletResponse response) throws IOException {
		int cnt = dealService.updatePrchsStts(dVO);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (cnt > 0) {
			out.println("<script language='javascript'>");
			out.println("alert('[구매완료] 리뷰작성을 완료해주세요'); history.go(-2);");
			out.println("</script>");

			out.flush();
		} else {
			out.println("<script language='javascript'>");
			out.println("alert('[구매실패] 이전 페이지로 이동합니다.'); history.back();");
			out.println("</script>");
		}
	}

	// ===========================
	// ▷ 중고거래 게시글 수정폼
	@GetMapping("/my/dealupdate/{dlNo}")
	public String updateTest(Model model, HttpServletRequest request, @PathVariable int dlNo) {
		// 임시로그인 : 세션에 아이디, 거래지역 담기
		HttpSession session = request.getSession();
		MemVO mVO = new MemVO();

		mVO.setUserId((String) session.getAttribute("userId"));
		mVO = memService.selectUser(mVO);

		// 게시글 정보 담기
		model.addAttribute("dealInfo", testService.selectDealTest(dlNo)); // n번 게시글 정보
		model.addAttribute("atchList", testService.selectDealAtchTest(dlNo)); // n번 게시글 첨부파일 목록
		model.addAttribute("category", codeService.codeList("002")); // 카테고리 정보

		return "deal/dealUpdateForm";
	}

	// ===========================
	// ▷ 중고거래 게시글 삭제
	// System.out.println(dlNo + " => 삭제할 글 번호");
	// System.out.println(dVO + " => 삭제할 글 정보");
	// System.out.println(atchList + " => 삭제할 첨부파일들 정보");
	// System.out.println("게시글 삭제했으면 1 => " + delDeal);
	// System.out.println("첨부파일 삭제했으면 1 이상 => " + delAtch);
	@RequestMapping("/my/deleteDeal/{dlNo}")
	public void deleteDeal(@PathVariable int dlNo, HttpServletResponse response) {

		DealVO dVO = testService.selectDealTest(dlNo);
		List<AtchVO> atchList = testService.selectDealAtchTest(dlNo);
		int delDeal = testService.deleteDealTest(dVO);
		int delAtch = atchService.deleteFile(atchList);

		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (delDeal > 0) {

				out.println("<script language='javascript'>");
				out.println("alert('[삭제완료] 게시글 삭제가 정상적으로 완료되었습니다. :D '); location.href='/goguma/dealMain';");
				out.println("</script>");
				out.flush();

			} else {
				out.println("<script language='javascript'>");
				out.println("alert('[삭제실패] 게시글 삭제 중 오류가 발생하였습니다 :( '); location.href='/goguma/dealMain';");
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ===========================
	// ❤❤ 판매자 페이지 더보기

	@RequestMapping("/goguma/dealSellerpage/{ntslId}/{dlNo}")
	public String getDealSeller(@PathVariable String ntslId, @PathVariable int dlNo, Paging paging, DealSearchVO svo,
			DealRvSearchVO rvo, Model model) {
		// 판매자가 남긴 리뷰 가져오는거
		paging.setPageUnit(3); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		rvo.setFirst(paging.getFirst());
		rvo.setLast(paging.getLast());

		rvo.setUserId(ntslId);
		paging.setTotalRecord(rvService.getcountTotal(rvo));

		model.addAttribute("review", rvService.getDealRv(rvo));// 여러건의 후기 조회 rvo로 담아야 페이징가능하다.

		// 판매중인 물품 first last 이상하다 => 페이징을 두번해서 생기는결과. 일단 last값 강제로 주고 띄우기함
		DealVO vo = dealService.selectDeal(dlNo);
		svo.setDlNo(dlNo);

		paging.setPageUnit(3); // 한 페이지에 출력할 레코드 건수

		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		svo.setFirst(paging.getFirst());
		svo.setLast(10);
		svo.setNtslId(ntslId);

		paging.setTotalRecord(dealService.getcountTotal(svo));

		model.addAttribute("dealList", dealService.selectNtslDeal(svo)); // 판매자 정보를 보여줄 모델

		// 판매자 후기 정보 // ❤❤ 넣어야함!!!
		model.addAttribute("vote", voteService.getDealRvVote(ntslId)); // 여러건의 후기 투표 조회
		return "deal/dealSellerpage";

	}

	
	/////////////////// [[ 2. 마이페이지 ]]
	// ===========================
	// ▷ 마이페이지 / 중고거래 : 거래내역
	@RequestMapping("/my/myDeal")
	public String mydeal(String userId, Model model, HttpServletRequest request, Paging paging, DealSearchVO svo) {
		HttpSession session = request.getSession();
		userId = (String) session.getAttribute("userId");
		// 판매중 내역
		svo.setNtslId(userId);
		svo.setStts("판매중");

		paging.setPageUnit(1); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(dealService.getcountTotal(svo));

		model.addAttribute("dealList", dealService.selectNtslDeal(svo)); // 판매중 내역

		// 판매완료 내역
		DealSearchVO searchvo = new DealSearchVO();
		searchvo.setStts("판매완료");
		model.addAttribute("dealsold", dealService.selectNtslDeal(searchvo)); // 판매완료 내역

		searchvo = new DealSearchVO();
		searchvo.setPrchsId(userId);
		model.addAttribute("buyList", dealService.selectNtslDeal(searchvo)); // 구매내역

		return "myPages/myDeal";
	}

	// ===========================
	// ▷ 마이페이지 / 중고거래 : 끌어올리기 데이터는 impl에서 vo담았음
	@RequestMapping("/my/myDealSubmit") // 게시글업데이트..
	@ResponseBody
	public int mydeal(Model model, DealVO dVO, PointVO pVO, String userId, HttpSession session) {
		userId = (String) session.getAttribute("userId");

		int cnt = dealService.updateYmd(dVO, userId); // pointVO에 userId담아줄라공~

		return cnt;
	}

	// ===========================
	// ▷ 마이페이지 / 중고거래 가계부 : 이것도 왠지 셀렉트문 두개 말고.. 매퍼 쿼리에서 where if문으로 제어해야될거같은데
	@RequestMapping("/my/myCashbook")
	public String mycashbook(String userId, Model model, HttpSession session) {
		userId = (String) session.getAttribute("userId");

		model.addAttribute("cashSell", dealService.selectCashNtsl(userId)); // 판매자일때 건수+데이터조회
		model.addAttribute("cashBuy", dealService.selectCashPrchs(userId)); // 구매자일때 건수+데이터조회
		return "myPages/myCashbook";
	}

	// ===========================
	// ▷ 마이페이지 / 중고거래 거래후기 : 저거 모델 쓰면 스트링?하면안된다고뜸 | 페이징하려면 리뷰테이블의 서치보가 따로 있어야하는데 없음
	@RequestMapping("/my/myReview")
	public String myreview(String userId, Model model, HttpServletRequest request, DealRvSearchVO rvo, Paging paging) {
		HttpSession session = request.getSession();
		userId = (String) session.getAttribute("userId");

		// 보낸 리뷰
		rvo.setUserId(userId);

		paging.setPageUnit(3); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		rvo.setFirst(paging.getFirst()); //
		rvo.setLast(paging.getLast()); //

		paging.setTotalRecord(rvService.getcountTotal(rvo));

		model.addAttribute("review", rvService.getDealRv(rvo));// 여러건의 후기 조회 rvo로 담아야 페이징가능하다.

		return "myPages/myReview";
	}

	
	/////////////////// [[ 3. 관리자 ]]
	// ===========================
	// ▶ 관리자 실검 관리
	@RequestMapping("/admin/adminKeywordbox") //

	public String adminKeywordbox(Model model, @ModelAttribute("dsvo") DealSearchVO svo) {

		model.addAttribute("word", searchService.getPopularWord()); // stts=1인 실검가져오기

		return "admin/adminKeywordbox"; // 뷰페이지명
	}

	// ▶ 관리자 임시 실검 => 완전한 실검으로 등록하기
	@PostMapping("/admin/adminKeywordboxsubmit")
	@ResponseBody
	public String adminKeywordboxsubmit(SearchVO svo) {
		List<SearchVO> list = svo.getSList();
		searchService.updateWord(list); // 검색어 담아서 업데이트 하셔야죠

		return "redirect:/goguma/dealMain";
	}

	// ▶ 관리자 임시 실검 삭제
	@GetMapping("/admin/deletekeyword/{keyword}")
	@ResponseBody
	public int delKeywordAjax(@PathVariable String keyword) {

		// 매퍼에 보낼 서치보생성
		SearchVO sVO = new SearchVO();
		sVO.setSearchTtl(keyword);
		int cnt = searchService.deleteWord(sVO);
		return cnt;
	}

	// ===========================
	// ▷ 판매중 전체조회 => 관리자페이지로 가면안되겟냥
	@RequestMapping("/admin/adminDeal") // 판매상품 전체 조회
	public String adminDeal(Model model, @ModelAttribute("dsvo") DealSearchVO svo, Paging paging) {

		paging.setPageUnit(5); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		paging.setTotalRecord(dealService.getcountTotal(svo)); // 현재 deal테이블에 있는 총 데이터건수

		model.addAttribute("lists", dealService.dealListSelect(svo));
		model.addAttribute("category", codeService.codeList("002")); // 공통코드
		return "admin/adminDeal"; // 뷰페이지명
	}

}
