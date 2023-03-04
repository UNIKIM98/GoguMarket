package com.goguma.common.serviceImpl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.mapper.AtchMapper;
import com.goguma.common.service.AtchService;
import com.goguma.common.vo.AtchVO;

@Service
public class AtchServiceImpl implements AtchService {

	@Autowired
	AtchMapper atchMapper;

	@Value("${goguma.save}")
	private String saveFolder;

	@Override
	public int insertFile(List<MultipartFile> files) {
		System.out.println("왔니....... commonFileUpload");
		// ▶ 파일 저장 위치 설정

		// ▶ atchId(첨부파일 없으면 0 리턴)
		int atchId = 0;

		// ▶ 파일이 존재하면 if문 실행
		if (files != null && !files.isEmpty()) {
			atchId = atchMapper.selectAtchId();
			System.out.println("atchId========" + atchId);

			// ▶ 파일 개수만큼 for문
			for (MultipartFile file : files) {
				System.out.println("==== for문 들어왔고(첨부파일O)");
				if (file.getSize() == 0)
					continue;

				// ▶ insert할 atchVO 생성
				AtchVO atch = new AtchVO();

				// ▶ 파일이름 중복시 덮어쓰기 되는 거 방지하기 위해 uuid 생성
				String fileName = UUID.randomUUID().toString();

				// ▶ uuid에 원본파일명 붙이기
				fileName = fileName + "_" + file.getOriginalFilename();
				System.out.println("==== fileName에 uuId 붙였슈 > "+fileName);

				// ▶ 파일 실제 저장
				File uploadFile = new File(saveFolder, fileName);
				System.out.println("==== file 저장할 곳은 > "+uploadFile);

				try {
					file.transferTo(uploadFile); // 실제파일저장
				System.out.println("==== file 저장 완");

				} catch (Exception e) {
					e.printStackTrace();
				}

				// ▶ 파일 저장하고 나서 atch 테이블에 insert할 vo에 값 담아주기
				atch.setAtchId(atchId);
				// atchNo : 메퍼에서 시퀀스로 생성함
				atch.setAtchNm(fileName);
				// crtYmd : 메퍼에서 sysdate로 생성
				atch.setOrgnlNm(file.getOriginalFilename());
				atch.setExtn("jpg"); // ※ 변경해야함! 파일타입 : jpg, jpeg, img, png, gif
				atch.setAtchSize(file.getSize());
				atch.setAtchPath("/upload/" + fileName);
				System.out.println("==== ATCH테이블에 들어갈 최종 VO > "+atch);
				// ▶ 테이블에 파일 저장
				atchMapper.insertFile(atch);
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

	@Override
	public int deleteFile(List<AtchVO> atchVOs) {
		System.out.println("삭제하러 impl 왔듬====");
		int cnt = 0;
		if (atchVOs != null && !atchVOs.isEmpty()) {
			System.out.println("if문 통과 =====");

			for (AtchVO atch : atchVOs) {
				
				System.out.println("for문 통과 =====");
				atch = atchMapper.selectFile(atch);
				
				File file = new File(saveFolder + atch.getAtchNm());
				System.out.println("atchPath로 파일 가지고 왔고 ==== (" + file);

				boolean result = file.delete();
				System.out.println("삭제했으면 true ==== " + result);
				
				if (result) {
					System.out.println("파일 삭제했고 db에서 삭제할 예정===");
					cnt += atchMapper.deleteFile(atch);
					
					System.out.println("db삭제 완료 했으면 1 이상=====" + cnt);
				}
			}
		}
		return cnt;
	}

	@Override
	public AtchVO selectFile(AtchVO atchVO) {
		return atchMapper.selectFile(atchVO);
	}

}
