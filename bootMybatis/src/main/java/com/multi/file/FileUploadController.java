package com.multi.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	@RequestMapping("/fileUploadForm")
	public String viewUploadForm() {
		return "upload/fileUploadForm";
	}
	
	@RequestMapping("/fileUpload")
	public String upload(@RequestParam("uploadFile") MultipartFile file, Model model) throws IOException {
		String savedFileName ="";
		
		//파일 저장 경로 설정 : 실제 서비스 되는 위치(프로젝트 외부에 저장)
		String uploadPath="c:/upload/";
		
		//원본 파일 이름
		String originalFileName = file.getOriginalFilename();
		
		//파일 이름이 중복 되지 않도록 파일 이름 변경 : 서버에 저장할 파일 이름
		//UUID 사용
		UUID uuid = UUID.randomUUID();
		savedFileName = uuid.toString() + "_" + originalFileName;
		
		//파일 생성
		File file1 = new File(uploadPath + savedFileName);
		
		//서버로 전송
		file.transferTo(file1);
		
		model.addAttribute("originalFileName", originalFileName);
		
		return "upload/fileUploadResult";
	}
}
