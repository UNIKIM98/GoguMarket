package com.goguma.rsvt.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.biz.service.BizMemService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.mem.service.MemService;
import com.goguma.rsvt.service.BizMenuService;
import com.goguma.rsvt.service.RsvtService;
import com.goguma.rsvt.vo.RsvtMenuVO;
import com.goguma.rsvt.vo.RsvtPaymentVO;
import com.goguma.rsvt.vo.RsvtUpdateVO;
import com.goguma.rsvt.vo.RsvtVO;

@Controller
public class RsvtController {

	/*
	 * book01~05 => 어떤건 biz에 있고 어떤건 rsvt에 있어서 다 모아놈 BizController에 있음.
	 */

//	@GetMapping("/book0601")
//	public String book0601() {
//		return "rsvt/book0601";
//	}

	@Autowired
	BizMemService bizMemService; // 가게정보
	@Autowired
	BizMenuService menuService; // 메뉴 들고오기 위함
	@Autowired
	CommonCodeService codeService; // 공통코드
	@Autowired
	RsvtService rsvtService; // 예약
	@Autowired
	MemService memService; // 맴바정보
	
	

	// 일반예약
	@RequestMapping("/my/book0601/{bizNo}")
	public String bizInfo(@PathVariable String bizNo, Model model) {

		model.addAttribute("biz", bizMemService.bizInfo(bizNo)); // 가게정보
		model.addAttribute("menu", menuService.bizMenu(bizNo)); // 메뉴
		model.addAttribute("code", codeService.codeList("007")); // 공통코드(시간)

		return "rsvt/book0601";
	}

	// 함께예약
	@GetMapping("/my/book0602/{bizNo}")
	public String bizComInfo(@PathVariable String bizNo, Model model) {

		model.addAttribute("biz", bizMemService.bizInfo(bizNo)); // 가게정보
		model.addAttribute("menu", menuService.bizMenu(bizNo)); // 메뉴
		model.addAttribute("code", codeService.codeList("007")); // 공통코드(시간)

		return "rsvt/book0602";
	}

	@GetMapping("/book0603")
	public String book0603() {
		return "rsvt/book0603";
	}

	// 예약완료
	@GetMapping("/my/rsvtComplete/{rsvtNo}")
	public String book0604(@PathVariable int rsvtNo, Model model) {
		//System.out.println(rsvtNo);
		model.addAttribute("info", rsvtService.selectRsvtOne(Integer.toString(rsvtNo)));
		model.addAttribute("mn", rsvtService.selectMyRsvtDetail(Integer.toString(rsvtNo)));
		return "rsvt/book0604";
	}
	

	// 예약내역
	@GetMapping("/my/myReservation")
	public String mybook01(String userId, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		userId = (String) session.getAttribute("userId");
		

		//List<Map> Lists = rsvtService.selectMyRsvtDetail(userId); =>아작스로 교체
		List<Map> simple = rsvtService.selectMyRsvtList(userId);

		//System.out.println("프린트2 : " + Lists.size() + ", " + simple.size());

		//model.addAttribute("lists", Lists);
		model.addAttribute("simple", simple);
		model.addAttribute("code", codeService.codeList("007"));
		
		return "myPages/mybook01";

	}
	
	//예약상세내역 메뉴부분 ajax(mybook01, 02에서 사용)
	@GetMapping("/my/myRsvtAjax")
	@ResponseBody
	public List<Map> myRsvtAjax(RsvtVO vo) {
		
		int rsvtNo = vo.getRsvtNo();
		System.out.println("예약번호=="+rsvtNo);
				
		return rsvtService.selectMyRsvtDetail(Integer.toString(rsvtNo));
	}
	
	//예약상세내역 예악자부분 ajax
	@GetMapping("/my/myRsvtInfoAjax")
	@ResponseBody
	public RsvtVO myRsvtInfoAjax(RsvtVO vo) {
		int rsvtNo = vo.getRsvtNo();
		return rsvtService.selectRsvtOne(Integer.toString(rsvtNo));
	}
	
	
	//예약수정
	@GetMapping("/my/mybook02/{rsvtNo}")
	public String mybook02(@PathVariable int rsvtNo, Model model) {
		//System.out.println("mybook02페이지 예약번호 출력===" + rsvtNo);
		
		model.addAttribute("rsvt",rsvtService.selectRsvtOne(Integer.toString(rsvtNo)));
//		model.addAttribute("menu", rsvtService.selectRsvtBizMenu(Integer.toString(rsvtNo)));
//		model.addAttribute("code", codeService.codeList("007"));
//		model.addAttribute("info", rsvtService.selectMyRsvtDetail(Integer.toString(rsvtNo)));
		
		return "myPages/mybook02";
	}

	@GetMapping("/mybook04")
	
	public String mybook04() {
		return "myPages/mybook04";
	}

	// 예약페이지 form submit - js gotoOrder()함수에서 ajax 호출한거 받아옴
	@PostMapping("/my/orderFormSubmit")
	@ResponseBody
	public int orderFormSubmit(RsvtVO rsvtInfo, RsvtMenuVO menuInfo, HttpServletRequest request) {
		// 세션에서 로그인한 아이디 불러오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		// 예약테이블 insert
		rsvtInfo.setUserId(userId); // 아이디
		rsvtInfo.setRsvtStts("예약완료"); // 예약상태
		rsvtInfo.setComOrderYn("N"); // 함께주문여부
		rsvtInfo.setAlarmYn("Y"); // 알림여부

		System.out.println("====" + rsvtInfo);

		// 예약정보 insert
		rsvtService.insertRsvtInfo(rsvtInfo);

		// 예약메뉴 불러오기 위한 list
		List<RsvtMenuVO> lists = menuInfo.getMenuInfo();
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(i + "번째 메뉴.");
			System.out.println(lists.get(i).getMenuNm()); // 각 예약메뉴 확인
			System.out.println(lists.get(i).getAmount());
			System.out.println(lists.get(i).getMenuNo());

			// 예약메뉴 정보에 userId 넣어주기
			lists.get(i).setUserId(userId);
			// RsvtVO에서 가져온 예약번호를 RsvtMenuVO로 넘겨주기
			lists.get(i).setRsvtNo(rsvtInfo.getRsvtNo());

			System.out.println("=======" + lists.get(i));

			// 예약메뉴테이블 insert
			rsvtService.insertRsvtMenu(lists.get(i));

		}
		int rsvtNo = rsvtInfo.getRsvtNo();
		System.out.println(rsvtNo + "====================제발");

		// 예약번호를 ajax의 리턴값으로 돌려줌 -> js gotoOrder()함수에서 result값으로 확인가능
		return rsvtNo;
	}

	// 결제정보 저장 - js saveRsvtPay()함수에서 ajax 호출한거 받아옴
	@PostMapping("/my/insertRsvtPayment")
	@ResponseBody
	public int insertRsvtPayment(@RequestBody RsvtPaymentVO payVo) {
		/*
		 * @RequestBody : HTTP 요청 본문에 담긴 값들을 자바객체로 변환시켜 객체에 저장. 즉, ajax로 보낸 데이터를 payVo에
		 * 저장하겠다 이말이야 => 금액, 결제방법, 예약번호 가져옴
		 */
		System.out.println(payVo);

		int cnt = rsvtService.insertRsvtPayment(payVo); // 결제번호 카운팅 확인
		int rsvtNo = payVo.getRsvtNo(); // 예약번호
		System.out.println(rsvtNo + "예약번ㅎ======");
		System.out.println(cnt + "건 결제 완료");

		// 예약번호를 예약완료 페이지로 넘기기 위해 리턴값으로 넘겨줌 => ajaxㄱㄱ
		return rsvtNo;

	}
	
	@PostMapping("/my/requestModifyAjax")
	@ResponseBody
	public int insertRsvtUpdateTbl(RsvtUpdateVO rsvtUpdateVo) {
//		System.out.println("수정예약번호===="+rsvtNo);
		
		rsvtUpdateVo.setAprvYn("N");
		System.out.println(rsvtUpdateVo);
		return 0;
	}

	
}

