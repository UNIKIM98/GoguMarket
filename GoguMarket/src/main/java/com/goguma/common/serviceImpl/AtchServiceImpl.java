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

	// ===========================
	// ❤️ 다중파일 업로드
	@Override
	public int insertFile(List<MultipartFile> files) {
		int atchId = 0;

		if (files != null && !files.isEmpty()) {
			atchId = atchMapper.selectAtchId();

			for (MultipartFile file : files) {
				if (file.getSize() == 0)
					continue;

				AtchVO atch = new AtchVO();

				String fileName = UUID.randomUUID().toString();
				fileName = fileName + "_" + file.getOriginalFilename();

				File uploadFile = new File(saveFolder, fileName);
				try {
					file.transferTo(uploadFile);

				} catch (Exception e) {
					e.printStackTrace();
				}

				atch.setAtchId(atchId);
				atch.setAtchNm(fileName);
				atch.setOrgnlNm(file.getOriginalFilename());
				atch.setExtn("jpg"); // ※ 변경해야함! 파일타입 : jpg, jpeg, img, png, gif
				atch.setAtchSize(file.getSize());
				atch.setAtchPath("/upload/" + fileName);

				atchMapper.insertFile(atch);
			}

		}
		return atchId;
	}

	// ===========================
	// ❤️ 다중파일 수정
	@Override
	public int insertFile(int atchId, List<MultipartFile> files) {

		if (files != null && !files.isEmpty()) {
			for (MultipartFile file : files) {
				if (file.getSize() == 0)
					continue;

				AtchVO atch = new AtchVO();

				String fileName = UUID.randomUUID().toString();
				fileName = fileName + "_" + file.getOriginalFilename();

				File uploadFile = new File(saveFolder, fileName);
				try {
					file.transferTo(uploadFile);

				} catch (Exception e) {
					e.printStackTrace();
				}

				atch.setAtchId(atchId);
				atch.setAtchNm(fileName);
				atch.setOrgnlNm(file.getOriginalFilename());
				atch.setExtn("jpg"); // ※ 변경해야함! 파일타입 : jpg, jpeg, img, png, gif
				atch.setAtchSize(file.getSize());
				atch.setAtchPath("/upload/" + fileName);

				atchMapper.insertFile(atch);
			}
		}
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

	// ===========================
	// ❤️ 다중파일 삭제
	@Override
	public int deleteFile(List<AtchVO> atchVOs) {
		int cnt = 0;
		if (atchVOs != null && !atchVOs.isEmpty()) {

			for (AtchVO atch : atchVOs) {
				atch = atchMapper.selectFile(atch);

				File file = new File(saveFolder + atch.getAtchNm());
				System.out.println(file);
				boolean result = file.delete();
				
				if (result) {
					cnt += 1;
					atchMapper.deleteFile(atch);
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
