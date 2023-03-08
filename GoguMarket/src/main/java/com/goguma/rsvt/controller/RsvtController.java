package com.goguma.rsvt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.biz.service.BizMemService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.mem.service.MemService;
import com.goguma.rsvt.service.BizMenuService;
import com.goguma.rsvt.service.RsvtService;
import com.goguma.rsvt.vo.RsvtMenuVO;
import com.goguma.rsvt.vo.RsvtPaymentVO;
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

	@RequestMapping("/book0601/{bizNo}")
	public String bizInfo(@PathVariable String bizNo, Model model) {

		model.addAttribute("biz", bizMemService.bizInfo(bizNo)); // 가게정보
		model.addAttribute("menu", menuService.bizMenu(bizNo)); // 메뉴
		model.addAttribute("code", codeService.codeList("007")); // 공통코드(시간)

		return "rsvt/book0601";
	}

	@GetMapping("/book0602")
	public String book0602() {
		return "rsvt/book0602";
	}

	@GetMapping("/book0603")
	public String book0603() {
		return "rsvt/book0603";
	}

	@GetMapping("/book0604/{rsvtNo}")
	public String book0604(@PathVariable int rsvtNo) {
		System.out.println(rsvtNo);
		
		return "rsvt/book0604";
	}

	@GetMapping("/mybook01")
	public String mybook01() {
		return "myPages/mybook01";
	}

	@GetMapping("/mybook02")
	public String mybook02() {
		return "myPages/mybook02";
	}

	@GetMapping("/mybook04")
	public String mybook04() {
		return "myPages/mybook04";
	}

	// 예약페이지 form submit
	@PostMapping("/orderFormSubmit")
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
		rsvtService.insertRsvtInfo(rsvtInfo);

		List<RsvtMenuVO> lists = menuInfo.getMenuInfo();
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(i + "번째 도는 중.");
			System.out.println(lists.get(i).getMenuNm());
			System.out.println(lists.get(i).getAmount());
			System.out.println(lists.get(i).getMenuNo());
			lists.get(i).setUserId(userId);
			lists.get(i).setRsvtNo(rsvtInfo.getRsvtNo());
			System.out.println("=======" + lists.get(i));
			// 예약메뉴테이블 insert
			rsvtService.insertRsvtMenu(lists.get(i));

		}
		int rsvtNo = rsvtInfo.getRsvtNo();
		System.out.println(rsvtNo +"====================제발");
		return rsvtNo;
	}

	@PostMapping("/insertRsvtPayment")
	@ResponseBody
	public int insertRsvtPayment(@RequestBody RsvtPaymentVO payVo) {
		System.out.println(payVo);
		int cnt = rsvtService.insertRsvtPayment(payVo);
		int rsvtNo = payVo.getRsvtNo();
		System.out.println(rsvtNo+"예약번ㅎ======");
		System.out.println(cnt + "건 결제 완료");
		return rsvtNo;

	}

}
