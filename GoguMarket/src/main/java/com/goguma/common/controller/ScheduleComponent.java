package com.goguma.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;


@Component
public class ScheduleComponent {
	
	@Autowired
	MemService memService;
	
	@Scheduled(cron = "2 * * * * *")
	public void scheduleTest() {
		MemVO memVO = new MemVO();
		memVO.setUserId("uni");
		memService.selectUser(memVO);
		System.out.println("스케줄링 test");
	}
}
