package com.goguma.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.service.AtchService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.service.TestService;
import com.goguma.common.vo.AtchVO;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealVO;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@Controller
public class TestController {
	// ■ Services ==========================================
	@Autowired
	MemService memService;

	@Autowired
	AtchService atchService;

	@Autowired
	CommonCodeService codeService;

	@Autowired
	DealService dealService;

	@Autowired
	TestService testService;

	// ■ Controllers =======================================
	// ▷ 첨부파일 삭제 ajax 테스트 -------------------------------
	@GetMapping("/delteFileTest")
	public String delteFileTest() {
		// 파일삭제 테스트용 list
		List<AtchVO> files = new ArrayList<AtchVO>();

		// list에 임시로 하나 세팅
		AtchVO atchVO = new AtchVO();
		atchVO.setAtchId(13);
		atchVO.setAtchNo(98);
		files.add(atchVO);

		int delCnt = atchService.deleteFile(files);
		if (delCnt > 0) {
			System.out.println(delCnt + "건 삭제완료");
		}
		return "myPages/myAttend";
	}

	// ▷ insertForm 테스트 -----------------------------------
	@GetMapping("/insertTest")
	public String testForm(Model model) {
		model.addAttribute("category", codeService.codeList("002"));
		return "common/cmInsertForm";
	}

	@RequestMapping("/testFormSubmit")
	@ResponseBody
	public DealVO testFormSubmit(DealVO vo, List<MultipartFile> files) {
		int atchId = atchService.insertFile(files);

		System.out.println("==============================" + vo);
		System.out.println(vo.getNegoYn() != "Y");
		if (atchId > 0) {
			vo.setAtchId(atchId);
		}
		dealService.insertDeal(vo);

		return vo;
	}

	// ▷ updateForm 테스트 -----------------------------------
	@GetMapping("/updateTest")
	public String updateTest(Model model, HttpServletRequest request) {
		// 임시로그인 : 세션에 아이디, 거래지역 담기
		HttpSession session = request.getSession();
		MemVO mVO = new MemVO();

		mVO.setUserId("user1");
		mVO = memService.selectUser(mVO);

		session.setAttribute("userId", mVO.getUserId()); // 유저id 설정
		session.setAttribute("dealArea", mVO.getDealArea()); // 유저 거래지역 설정

		// 게시글 정보 담기
		model.addAttribute("dealInfo", testService.selectDealTest(7)); // 12번 게시글 정보

		model.addAttribute("atchList", testService.selectDealAtchTest(7)); // 12번 게시글 첨부파일 목록

		model.addAttribute("category", codeService.codeList("002")); // 카테고리 정보

		return "common/cmUpdateForm";
	}

	@RequestMapping("/deleteTest")
	@ResponseBody
	public int deleteFileTest(@RequestBody List<AtchVO> deleteList) {
		int cnt = atchService.deleteFile(deleteList);
		return cnt;
	}

	@RequestMapping("/updateTestSubmit")
	@ResponseBody
	public DealVO updateTestSubmit(DealVO vo, List<MultipartFile> files) {

		System.out.println("updateTestSubmit왔음 =======");

		int fileUpdateCnt = atchService.insertFile(vo.getAtchId(), files);
		System.out.println("넣을 파일 리스트(atchvo)" + files);
		System.out.println("수정할 dealVO ==============================" + vo);

		testService.updateDealTest(vo);

		return vo;
	}

	// ▷ delete 테스트 -----------------------------------
	@RequestMapping("/deleteAllTest/{dlNo}")
	public String deleteAllTest(@PathVariable int dlNo) {
		System.out.println(dlNo + " => 삭제할 글 번호");

		DealVO dVO = testService.selectDealTest(dlNo);
		System.out.println(dVO + " => 삭제할 글 정보");

		List<AtchVO> atchList = testService.selectDealAtchTest(dlNo);
		System.out.println(atchList + " => 삭제할 첨부파일들 정보");

		int delDeal = testService.deleteDealTest(dVO);
		System.out.println("게시글 삭제했으면 1 => " + delDeal);

		int delAtch = atchService.deleteFile(atchList);
		System.out.println("첨부파일 삭제했으면 1 이상 => " + delAtch);

		return "deal/dealMain";
	}

}
