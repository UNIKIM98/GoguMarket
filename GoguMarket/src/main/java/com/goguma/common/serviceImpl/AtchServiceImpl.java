package com.goguma.common.serviceImpl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.mapper.AtchMapper;
import com.goguma.common.service.AtchService;
import com.goguma.common.vo.AtchVO;


@Service
public class AtchServiceImpl implements AtchService {

	@Autowired
	AtchMapper atchMapper;

	@Override
	public int fileUpload(List<MultipartFile> files) {

		System.out.println("impl  왔음==============");
		// ▶ 파일 저장 위치 설정
		String saveFolder = ("C:\\upload/");

		// ▶ atchId(첨부파일 없으면 0 리턴)
		int atchId = 0;

		// ▶ 파일이 존재하면 if문 실행
		if (files != null && !files.isEmpty()) {
			atchId = atchMapper.selectAtchId();
			System.out.println("atchId===========" + atchId);

			// ▶ 파일 개수만큼 for문
			for (MultipartFile file : files) {

				// ▶ insert할 atchVO 생성
				AtchVO attach = new AtchVO();

				// ▶ 파일이름 중복시 덮어쓰기 되는 거 방지하기 위해 uuid 생성
				String fileName = UUID.randomUUID().toString();

				// ▶ uuid에 원본파일명 붙이기
				fileName = fileName + "_" + file.getOriginalFilename();

				// ▶ 파일 실제 저장
				File uploadFile = new File(saveFolder, fileName);

				try {
					file.transferTo(uploadFile); // 실제파일저장
				} catch (Exception e) {
					e.printStackTrace();
				}

				// ▶ 파일 저장하고 나서 atch 테이블에 insert할 vo에 값 담아주기
				attach.setAtchId(atchId);
				// atchNo : 메퍼에서 시퀀스로 생성
				attach.setAtchNm(fileName);
				// crtYmd : 메퍼에서 sysdate로 생성
				attach.setOrgnlNm(file.getOriginalFilename());
				attach.setExtn("jpg"); // ※ 변경해야함! 파일타입 : jpg, jpeg, img, png, gif
				attach.setAtchSize(file.getSize());
				attach.setAtchPath(saveFolder + fileName);

				// ▶ 테이블에 파일 저장
				atchMapper.fileUpload(attach);
			}

		}
		System.out.println("atchId ==============" + atchId);
		return atchId;
	}

	@Override
	public int selectAtchId() {
		return atchMapper.selectAtchId();
	}


	@Override
	public List<AtchVO> selectAtch(int atchId) {
		return atchMapper.selectAtch(atchId);
	}

}
