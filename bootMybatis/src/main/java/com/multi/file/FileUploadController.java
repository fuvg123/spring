package com.multi.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	  public String  upload(@RequestParam("uploadFile") MultipartFile file,
			  									Model model) throws
	  IOException {
	  
	  String savedFileName = "";
	  
	  //1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
	  String uploadPath =  "c:/upload/";
	  
	  //2.원본 파일 이름
	  String originalFileName = file.getOriginalFilename();
	  
	  //3. 파일 이름이 중복되지 않도록 파일 이름 변경 : 서버에 저장할 파일 이름 // UUID 사용 
	  UUID uuid =  UUID.randomUUID(); 
	  savedFileName = uuid.toString() + "_" + originalFileName;
	  
	  //4. 파일 생성 
	  File file1 = new File(uploadPath + savedFileName);
	  
	  //5. 서버로 전송
	  file.transferTo(file1);
	  
	  model.addAttribute("originalFileName", originalFileName);
	  
	  return "upload/fileUploadResult"; 
	  }
	 
	  @RequestMapping("/fileUploadMultiple")
	  public String uploadFileMultiple(@RequestParam("uploadFileMulti") ArrayList<MultipartFile> files,
			  												Model model) throws IOException {
		  
		  String savedFileName = "";
		  String uploadPath = "c:/upload/";
		  
		  //여러 개의 파일 이름을 저장할 리스트
		  ArrayList<String> originalFilenameList = new ArrayList<String>();
		  
		  for(MultipartFile file : files) {
			  // 원본 파일 이름 가져오기
			  String originalFileName = file.getOriginalFilename();
			  //파일 이름을 리스트에 저장
			  originalFilenameList.add(originalFileName);
			  
			  // 파일 이름 변경 (중복되지 않도록 UUID 사용)
			  UUID uuid = UUID.randomUUID();
			  savedFileName = uuid.toString() + "_" + originalFileName;
			  
			  // 파일 생성
			  File file1 = new File(uploadPath + savedFileName);
			  
			  // 파일을 서버로 전송
			  file.transferTo(file1);
		  }
		  
		  model.addAttribute("originalFilenameList",  originalFilenameList);
		  
		  return "upload/fileUploadMultipleResult";
	  }
	  
	  @RequestMapping("/fileImageUpload") 
	  public String  imageUpload(@RequestParam("uploadFile") MultipartFile file,
			  									Model model) throws  IOException {
		  
		  //1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
		  String uploadPath =  "c:/upload/image/";
		  
		  //2.원본 파일 이름
		  String originalFileName = file.getOriginalFilename();  
		  
		  //3. 파일 생성 
		  File file1 = new File(uploadPath + originalFileName);
		  
		  //4. 서버로 전송
		  file.transferTo(file1);
		  
		  model.addAttribute("originalFileName", originalFileName);
		  
		  return "upload/fileImageUploadResult"; 
	  }
	
	
}

















