package com.githrd.www.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import com.githrd.www.dao.*;
import com.githrd.www.util.FileUtil;
import com.githrd.www.vo.*;

public class BoardService {

	@Autowired
	BoardDao bDao;
	
	//단일 파일 업로드 처리함수
	public FileVO uploadProc(MultipartFile file) {
		// 반환값 변수
		FileVO fVO = uploadProc(file, "/upload");

		return fVO;
	}

	public FileVO uploadProc(MultipartFile file, String dir) {
		/*
		 	이 함수가 파일을 업로드 하기 위해서는
		 	컨트롤러에서 업로드할 파일의 정보를 받아와야한다.
		 	
		 	여러개의 파일 중 이 함수에서는 한개의 파일만 처리하도록 하자.
		 */
		
		//반환값 변수
		FileVO fVO = new FileVO();
		
		//저장 경로를 기억할 변수
		String path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.indexOf("/WEB-INF")) + "/resources" + dir;
		
		fVO.setDir("/www" + dir + "/");
		
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

	// 다중 파일 업로드 기능 처리함수
	public ArrayList<FileVO> uploadProc(MultipartFile[] file){
		ArrayList<FileVO> list = new ArrayList<FileVO>();

		for(MultipartFile f : file) {
			list.add(uploadProc(f));
		}

		return list;
	}

	// 데이터베이스 입력작업 전담 처리함수
	@Transactional
	public void addBoardData(BoardVO bVO) {
		// 할일
		// 게시판 테이블에 데이터 입력하고
		bDao.addBoard(bVO);
		
		if(bVO.getFile() != null) {
			// 파일정보테이블에 파일정보들 입력하고(반복)
			ArrayList<FileVO> list = uploadProc(bVO.getFile());
			// bno를 꺼내서 FileVO들에 채워주고
			for(FileVO f : list) {
				f.setBno(bVO.getBno());
			}

			// 데이터 입력작업을 파일 갯수만큼 반복해준다.\
			for(FileVO f : list) {
				bDao.addFile(f);
			}
		}
	}

	// 게시글 수정 데이터베이스 작업 처리함수
	@Transactional
	public void editBoard(BoardVO bVO) {
		if(bVO.getTitle() != null || bVO.getBody() != null) {
			bDao.editBoard(bVO);
		}
		// 파일정보테이블에 파일정보들 입력하고(반복)
		if(bVO.getFile() != null) {
			ArrayList<FileVO> list = uploadProc(bVO.getFile());
			// bno를 꺼내서 FileVO들에 채워주고
			for(FileVO f : list) {
				f.setBno(bVO.getBno());
			}

			// 데이터 입력작업을 파일 갯수만큼 반복해준다.\
			for(FileVO f : list) {
				bDao.addFile(f);
			}
		}
	}
}