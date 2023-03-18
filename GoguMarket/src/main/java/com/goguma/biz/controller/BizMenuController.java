package com.goguma.biz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.biz.service.BizMemService;
import com.goguma.biz.vo.BizMemVO;
import com.goguma.rsvt.service.BizMenuService;
import com.goguma.rsvt.vo.BizMenuVO;

@Controller
public class BizMenuController {
	@Autowired
	private BizMemService bizService;
	
	@Autowired
	private BizMenuService menuService;

	// ❤ 비즈 메뉴 관리 페이지로 이동
	@GetMapping("/biz/bizMenu")
	public String bizMenu(Model model, HttpSession session) {
		// 지영 언니 슬라이드 > 필수
		String bizNo = (String) session.getAttribute("bizNo");
		model.addAttribute("biz", bizService.bizInfo(bizNo));
		return "biz/bizMenu";
	}

	// 기존 메뉴 정보 ajax
	@GetMapping("/biz/getMenuAjax")
	@ResponseBody
	public List<BizMenuVO> getMenuAjax(HttpSession session){
		String bizNo = (String) session.getAttribute("bizNo");
		List<BizMenuVO> menuList = menuService.bizMenu(bizNo);
		System.out.println(menuList);
		
		return menuList;
		
	}
	
	//저장테스트
	@PostMapping("/biz/menuEdixAjax")
	@ResponseBody
	public String menuTest(BizMenuVO menuList) {
		System.out.println(menuList);
		System.out.println(menuList.getMenuList());
		System.out.println(menuList.getNewList());
		List<BizMenuVO> menu = menuList.getMenuList();
//		menuService.insertBizMenu();
		return "/";
	}
	
	@GetMapping("/biz/menuDelAjax/{menuNo}")
	@ResponseBody
	public void menuDelAjax(@PathVariable int menuNo) {
		System.out.println(menuNo);
		menuService.deleteMenu(menuNo);
	}
	
	@GetMapping("/biz/insertMenu")
	public String insertMenu(Model model, HttpSession session) {
		String bizNo = (String) session.getAttribute("bizNo");
		model.addAttribute("biz", bizService.bizInfo(bizNo));
		return "biz/bizMenuInsert";
	}
}