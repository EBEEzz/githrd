package com.githrd.www.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.githrd.www.vo.*;

public class MemberDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	//로그인 처리
	public int getLogin(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.login", mVO);
	}
	
	//아이디 카운트 조회
	public int getIdCnt(String id) {
		return sqlSession.selectOne("mSQL.idCnt", id);
	}
	
	// 아바타 리스트 조회 전담 처리함수
	public List<MemberVO> getAvtList(){
		return sqlSession.selectList("mSQL.avtList");
	}
	
	// 회원정보 데이터베이스 추가작업 전담 처리함수
	public int addMember(MemberVO mVO) {
		return sqlSession.insert("mSQL.addMember", mVO);
	}
}
