package com.goguma.auct.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.auct.service.AuctMemService;
import com.goguma.auct.service.AuctService;
import com.goguma.auct.vo.AuctMemVO;
import com.goguma.auct.vo.AuctVO;
import com.goguma.common.service.AtchService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.AtchVO;
import com.goguma.common.vo.CommonCodeVO;

@Controller
public class AuctController {

	@Autowired
	private AuctService auctService; // 경매 서비스영역
	@Autowired
	private AtchService atchService; // 첨부파일 서비스 영역
	@Autowired
	private AuctMemService auctMemService; // 경매 입찰자 영역
	@Autowired
	private CommonCodeService codeService; // 공통코드 영역

	// ▼전체 품목 리스트
	@GetMapping("/goguma/auctList")
	public String getauctList(Model model, AuctVO vo, AuctMemVO mvo) {
		// 각각 서비스의 실핼값을 지정된 이름의 model값에 담아줌
		model.addAttribute("lists", auctService.getAuctList());
		model.addAttribute("nowPrcs", auctMemService.selectNowPrc());

		return "auction/auctList";
	}
	// ▼전체 품목 리스트 AJAX 미사용시 제거
	@GetMapping("/goguma/auctListAjax")
	@ResponseBody
	public List<AuctVO> getauctListAjax(Model model, AuctVO vo, AuctMemVO mvo) {
		
		List<AuctVO> lists = auctService.getAuctList();
		
		System.out.println(lists);
		
		
		return lists;
	}
	// ▼단일품목 조회
	@GetMapping("/goguma/auctSelect/{auctNo}")
	public String auctSelect(@PathVariable int auctNo, Model model) {
		AuctVO vo = new AuctVO();
		vo.setAuctNo(auctNo);
		vo = auctService.getAuct(vo); // 단건조회 서비스
		int auctDday = auctService.auctDday(auctNo); // Dday서비스
		
		List<AtchVO> atchList = atchService.selectAtch(vo.getAtchId()); // 첨부파일서비스 리스트로 조회
		List<AuctMemVO> avoList = auctMemService.selectAuctMem(auctNo); // 입찰자 서비스 불러오기
		
		model.addAttribute("auct", vo); // 모델에 경매관련 내용 담아줌 이름은 auct
		model.addAttribute("atch", atchList); // 경매관련 첨부파일 담아줌 이름은 atch
		model.addAttribute("Dday", auctDday); // Dday계산기 담아줌
		model.addAttribute("file", auctService.selectAuctAtch(auctNo)); // 게시글 모든 이미지
		
		if (avoList.size() == 0) {
			model.addAttribute("auctMem", "nothing");
		} else {
			model.addAttribute("auctMem", avoList);
		}

		return "auction/auctSelect";
	}
	// ▼상품등록폼 이동
	@GetMapping("/my/auctInsertForm")
	public String auctInsertForm(Model model) {
		List<CommonCodeVO> codeList = codeService.codeList("002");
		codeList.remove(0);

		model.addAttribute("category", codeList);
		return "auction/auctInsertForm";

	}
	// ▼상품등록
	@PostMapping("/my/auctInsert")
	@ResponseBody
	public String auctInsert(AuctVO vo, List<MultipartFile> files, HttpServletRequest request) {
		String result = "fail";
		int atchId = atchService.insertFile(files);
		int atchList = atchService.insertFile(atchId, files);

		HttpSession session = request.getSession(); // 세션값 받아옴
		String myId = (String) session.getAttribute("userId"); // 세션값 중 아이디만 받아옴
		vo.setUserId(myId); // 세션값으로 아이디 설정
		
		if (vo.getQuickPrc() == 0) {
			System.out.println("즉구가 없음");
		}
		if (atchId > 0) {
			vo.setAtchId(atchId);
		}

		int cnt = auctService.insertAuct(vo); // vo값으로 auctService의 insertAuct를 실행
		if (cnt > 0) {
			result = "success";
		}
		return result;
	}
	// ▼전체 품목 리스트
	@RequestMapping("/my/auctDelete/{auctNo}")
	public void auctDelete(@PathVariable int auctNo, HttpServletResponse response) {
		AuctVO vo = new AuctVO();
		vo.setAuctNo(auctNo);
		vo = auctService.getAuct(vo);

		List<AtchVO> atchList = auctService.selectAuctAtch(auctNo); //삭제할 첨부파일 정보

		int delAuct = auctService.deleteAuct(vo); //경매 삭제 서비스
		int delAtch = atchService.deleteFile(atchList); //첨부파일 삭제 서비스

		try {
			if (delAuct > 0) {
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
	}
	// ▼마이페이지 등록한 경매품 리스트 이동
	@GetMapping("/my/allAuction")
	public String allAuction(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		List<AuctVO> myAuctList = auctService.selectUserId(userId); // 세션의(String)userId로 서비스 실행. 값은 여러개라 List입니다.

		model.addAttribute("myAuctList", myAuctList); // 실행된 값을 model에 담아줌

		return "auction/allAuction";

	}


}
