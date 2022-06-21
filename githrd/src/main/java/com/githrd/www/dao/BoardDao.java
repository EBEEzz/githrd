package com.githrd.www.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.githrd.www.util.*;
import com.githrd.www.vo.*;

public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 총 게시글 갯수 조회 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("bSQL.getTotal");
	}
	
	// 게시글 리스트 조회 전담 처리함수
	public List<BoardVO> getList(PageUtil page){
		return sqlSession.selectList("bSQL.getList", page);
	}
	
	// 게시글 번호로 첨부파일 리스트 조회 전담 처리함수
	public List<FileVO> getFileList(int bno){
		return sqlSession.selectList("bSQL.fileList", bno);
	}
	
	// 게시글 번호로 게시글 정보 조회 전담 처리함수
	public BoardVO getDetail(int bno) {
		return sqlSession.selectOne("bSQL.boardDetail", bno);
	}
	
	//게시글 입력 전담 처리함수
	public int addBoard(BoardVO bVO) {
		return sqlSession.insert("bSQL.insertBoard", bVO);
	}
	
	//첨부파일 정보 입력 전담 처리함수
	public int addFile(FileVO fVO) {
		return sqlSession.insert("bSQL.insertFile", fVO);
	}
}