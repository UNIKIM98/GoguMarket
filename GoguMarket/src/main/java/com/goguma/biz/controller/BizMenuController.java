package com.goguma.biz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

	@PostMapping("/biz/menuTest")
	public String menuTest(BizMenuVO menuList) {
		System.out.println(menuList.getMenuList());
		System.out.println(menuList.getNewList());
		List<BizMenuVO> menu = menuList.getMenuList();
//		menuService.insertBizMenu();
		return "/";

	}
}