package com.goguma.mem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.mem.service.PointService;
import com.goguma.mem.vo.PointVO;
 
@Controller
public class PointController {

	@Autowired
	PointService pointService;
	
	// ===========================================================
	// ❤️ 나의 포인트
	@GetMapping("/my/myPoint")
	public String myCouponNPoint() {
		return "myPages/myPoint";
	}
	
	// ===========================================================
	// ❤️ 회원 포인트 조회 Ajax
	@GetMapping("/my/myPointAjax")
	@ResponseBody
	public List<PointVO> myPointAjax(HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		List<PointVO> pointInfo = pointService.selectPointListForUser(userId);
		
		return pointInfo;
	}
}
