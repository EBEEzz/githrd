package com.githrd.www.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.multipart.*;

import com.githrd.www.dao.*;
import com.githrd.www.util.FileUtil;
import com.githrd.www.vo.*;

public class BoardService {

	@Autowired
	BoardDao bDao;
	
	//단일 파일 업로드 처리함수
	public FileVO uploadProc(MultipartFile file) {
		/*
		 	이 함수가 파일을 업로드 하기 위해서는
		 	컨트롤러에서 업로드할 파일의 정보를 받아와야한다.
		 	
		 	여러개의 파일 중 이 함수에서는 한개의 파일만 처리하도록 하자.
		 */
		
		//반환값 변수
		FileVO fVO = new FileVO();
		
		//저장 경로를 기억할 변수
		String path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.indexOf("/WEB-INF")) + "/resources/upload";
		
		fVO.setDir("/www/upload/");
		
		//파일 크기
		long len = file.getSize();
		fVO.setLen(len);
		
		
		//파일의 원이름을 꺼내오기
		String oldName = file.getOriginalFilename();
		if(oldName == null) {
			return fVO;
		}
		
		fVO.setOriname(oldName);
		//저장이름 만들기
		String rename = FileUtil.rename(path, oldName);
		
		fVO.setSavename(rename);
		
		//파일 업로드하기
		try {
			File f = new File(path, rename);
			
			file.transferTo(f);	//파일 내용 기록
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return fVO;
	}
}
