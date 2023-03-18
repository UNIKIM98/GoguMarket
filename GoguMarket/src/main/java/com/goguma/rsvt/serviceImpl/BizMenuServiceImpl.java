package com.goguma.rsvt.serviceImpl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.goguma.rsvt.mapper.BizMenuMapper;
import com.goguma.rsvt.service.BizMenuService;
import com.goguma.rsvt.vo.BizMenuVO;

@Service
public class BizMenuServiceImpl implements BizMenuService {
	@Value("${goguma.save}")
	private String saveFolder;

	@Autowired
	private BizMenuMapper map;

	// 가게메뉴
	@Override
	public List<BizMenuVO> bizMenu(String bizNo) {
		return map.bizMenu(bizNo);
	}

	@Override
	public int deleteMenu(int menuNo) {
		String atchId = map.selectBizMenuOne(menuNo).getAtchId();

		if (atchId.startsWith("/upload/")) {
			atchId = atchId.replace("/upload/", "");
		} else {
			atchId = atchId.replace("/home/", "");
		}

		File file = new File(saveFolder + atchId);
		System.out.println(file);

		int cnt = 0;

//		if(file.delete()) {
//			cnt = map.deleteMenu(menuNo);
//		};

		return cnt;
	}

	@Override
	public BizMenuVO selectBizMenuOne(int menuNo) {
		// TODO Auto-generated method stub
		return map.selectBizMenuOne(menuNo);
	}

}
