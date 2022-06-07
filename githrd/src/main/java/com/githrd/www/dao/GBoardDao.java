package com.githrd.www.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.githrd.www.util.*;
import com.githrd.www.vo.*;

public class GBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 방명록 작성 글 수 조회 전담 처리함수
	public int getMyCount(String id) {
		return sqlSession.selectOne("gSQL.myCount", id);
	}
	
	// 총게시글 수 조회 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("gSQL.totalCount");
	}
	
	// 게시글 리스트조회 전담 처리함수
	public List<BoardVO> getList(PageUtil page) {
		return sqlSession.selectList("gSQL.gBoardList", page);
	}
}