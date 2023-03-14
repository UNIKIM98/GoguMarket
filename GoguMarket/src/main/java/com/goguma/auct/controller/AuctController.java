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

	@GetMapping("/goguma/auctList")
	public String getauctList(Model model, AuctVO vo) {
		// 전체품목 리스트

		// 아래는 auctService의 getAuctList실행값을 model에 담고 이름은 lists라고 명명합니다.
		model.addAttribute("lists", auctService.getAuctList());
		model.addAttribute("nowPrcs", auctMemService.selectNowPrc());

//		int cnt = auctService.auctHitUpdate(auctNo); // 조회수 증가 (근데 고장남ㅋㅋ 나중에 고침~)
//		model.addAttribute(cnt); 리스트에서 뭐 클릭시 증가시켜주면? 근데 셀렉트 안에서 새로고침한다면?
		// 클릭수가 새로고침 조회수 조작은 의미없는 듯 그냥 리스트에서 클릭 고고

		System.out.println(model); // 모델 확인

		return "auction/auctList";
	}

	@GetMapping("/goguma/auctSelect/{auctNo}") // 경매 번호에 따른 단일페이지 번호 변경
	public String auctSelect(@PathVariable int auctNo, Model model) {
		// 단일품목 값

		AuctVO vo = new AuctVO();
		vo.setAuctNo(auctNo);
		
		int cnt = auctService.auctHitUpdate(auctNo); // 조회수 증가 (근데 고장남ㅋㅋ 나중에 고침~)
		vo = auctService.getAuct(vo); // 단건조회 서비스 불러오기

		List<AtchVO> atchList = atchService.selectAtch(vo.getAtchId()); // 첨부파일서비스 리스트로 조회
		System.out.println("=======atch" + atchList );
		
		List<AuctMemVO> avoList = auctMemService.selectAuctMem(auctNo); // 입찰자 서비스 불러오기
		System.out.println("======auctMem size================");
		System.out.println(avoList.size()==0);
		System.out.println(avoList.isEmpty());
		System.out.println("=====auctMem"+avoList);
		
		System.out.println("=======auct"+vo);
		

		model.addAttribute("auct", vo); // 모델에 경매관련 내용 담아줌 이름은 auct
		model.addAttribute("atch", atchList); // 경매관련 첨부파일 담아줌 이름은 atch

		if (avoList.size() == 0) {
			model.addAttribute("auctMem", "nothing");
		} else {
			model.addAttribute("auctMem", avoList); // 오류나면 여기한번 보기
		}

		System.out.println(avoList.equals(null) + "equals");
		System.out.println((avoList.size() == 0) + "size");

		System.out.println("조회수" + cnt); // 조회수 증가 확인
		System.out.println("첨부파일" + atchList); // 첨부파일 확인
		System.out.println("입찰자" + avoList); // 입찰자 확인
		System.out.println("단건조회VO" + vo); // vo값 확인

		return "auction/auctSelect";
	}

	@GetMapping("/my/auctInsertForm")
	public String auctInsertForm(Model model) {
		// 상품등록폼 이동

		List<CommonCodeVO> codeList = codeService.codeList("002");
		codeList.remove(0);

		model.addAttribute("category", codeList);
		return "auction/auctInsertForm";

	}

	@PostMapping("/my/auctInsert") // 등록 매핑
	@ResponseBody
	public String auctInsert(AuctVO vo, List<MultipartFile> files, HttpServletRequest request) {
		// ▲ 리턴타입 스트링으로 바꿔주기! :
		System.out.println(files + "======넘어온 파일들");
		String result = "fail";
		int atchId = atchService.insertFile(files);
		int atchList = atchService.insertFile(atchId, files);

		HttpSession session = request.getSession(); // 세션값 받아옴
		String myId = (String) session.getAttribute("userId"); // 세션값 중 아이디만 받아옴
		vo.setUserId(myId); // 세션값으로 아이디 설정
		if (vo.getQuickPrc() == 0) {
			System.out.println("즉구가 없음");
		}
		System.out.println(atchId + " : files/////////");
		System.out.println(vo);
		System.out.println(atchList);
		if (atchId > 0) {
			vo.setAtchId(atchId);
		}

		int cnt = auctService.insertAuct(vo); // vo값으로 auctService의 insertAuct를 실행
		if (cnt > 0) {
			result = "success";
		}
		return result;
	}

	@RequestMapping("/my/auctDelete/{auctNo}")
	public void auctDelete(@PathVariable int auctNo, HttpServletResponse response) {
		System.out.println(auctNo + " => 삭제할 글 번호");

		AuctVO vo = auctService.selectAuctNo(auctNo);
		System.out.println(vo + " => 삭제할 글 정보");

		List<AtchVO> atchList = auctService.selectAuctAtch(auctNo);
		System.out.println(atchList + " => 삭제할 첨부파일들 정보");

		int delAuct = auctService.deleteAuct(vo);
		System.out.println("게시글 삭제했으면 1 => " + delAuct);

		int delAtch = atchService.deleteFile(atchList);
		System.out.println("첨부파일 삭제했으면 1 이상 => " + delAtch);

		try {
			if (delAuct > 0) {
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
	}

	@GetMapping("/my/allAuction")
	public String allAuction(Model model, HttpServletRequest request) {
		// 마이페이지 나의 모든 경매 이동

		// 세션의 id값과 작성 글의 id값이 같은 게시물만 보여준다.
		HttpSession session = request.getSession();				//세션값을 가져옵니다
		String userId = (String)session.getAttribute("userId");	//세션값중 "userId"를 String으로가져오고 userId라 명명합니다.
		List<AuctVO> myAuctList = auctService.selectUserId(userId); //userId로 매퍼문 돌립니다. 값은 여러개라 List입니다.
		
		model.addAttribute("myAuctList",myAuctList);			//모델에 잘 요리된 myAuctList를 담아줍니다.
		
		
		// 낙찰자 ID클릭시 이름, 계좌 전송하는것 구현?
		System.out.println("==============================마이페이지" + model);

		return "auction/allAuction";

	}

	@GetMapping("/my/takePartAuction")
	public String takePartAuction(Model model, HttpServletRequest request) {
		// 마이페이지 낙찰자에게 이름, 계좌번호 보내기
//		HttpSession session = request.getSession();
//		String userId = (String)session.getAttribute("userId");
//		List<AuctVO> myAuctList = auctService.selectUserId(userId);
//		List<AuctMemVO> mybidList= auctMemService.bidAuction(userId);
		
		
		
		return "auction/takePartAuction";

	}

	@GetMapping("/my/hostedAuction")
	public String hostedAuction(Model model) {
		// 마이페이지 내가 참여한 경매 이동

		List<CommonCodeVO> codeList = codeService.codeList("002");
		codeList.remove(0);

		model.addAttribute("category", codeList);
		return "auction/hostedAuction";

	}

}
