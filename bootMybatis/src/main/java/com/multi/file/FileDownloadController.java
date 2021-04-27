package com.multi.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileDownloadController {
	//파일 다운로드 폼 출력 : upload 폴더의 모든 파일 목록 출력
	@RequestMapping("/fileDownloadForm")
	public ModelAndView viewDownloadForm() {
		ModelAndView mv = new ModelAndView();
		
		// c:/upload 폴더에 있는 전체 파일 목록 가져오기
		File path = new File("c:/upload");
		String[] filelist = path.list();
		
		mv.addObject("filelist", filelist);
		mv.setViewName("upload/fileDownloadForm");
		
		return mv;
	}
	
	@RequestMapping("/fileDownload/{file}")
	public void fileDownload(@PathVariable String file,
											    HttpServletResponse response) throws IOException {
		
		File f = new File("c:/upload/", file);
		
		//file 다운로드 설정
		response.setContentType("application/download");
		response.setContentLength((int)f.length());
		response.setHeader("Content-Disposition", "attatchment;filename=\""+file+"\"");
		
		//response 객체를 통해서 서버로부터 파일 다운 받음
		OutputStream os = response.getOutputStream();
		
		//파일 입력 객체 생성
		FileInputStream fis = new FileInputStream(f);
		FileCopyUtils.copy(fis, os);
		
		fis.close();
		os.close();		
	}	
	
}









