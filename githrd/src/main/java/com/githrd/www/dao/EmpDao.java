package com.githrd.www.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.githrd.www.vo.*;

public class EmpDao {
	@Autowired
	SqlSessionTemplate sqlSession;

	// 분류리스트조회 전담 처리함수
	public List<EmpVO> selList(EmpVO eVO){
		return sqlSession.selectList("eSQL.selList", eVO);
	}

	// 분류별 직원리스트 조회 전담 처리함수
	public List<EmpVO> partList(EmpVO eVO){
		return sqlSession.selectList("eSQL.partList", eVO);
	}

	// 사원 정보조회 전담 처리함수
	public EmpVO getInfo(EmpVO eVO) {
		return sqlSession.selectOne("eSQL.getInfo", eVO);
	}

	// 이니셜 리스트 조회 전담 처리함수
	public List<EmpVO> getIniList(){
		return sqlSession.selectList("eSQL.getInitial");
	}

	// 이니셜로 이름 리스트조회 전담 처리함수
	public List<EmpVO> nameList(EmpVO eVO){
		return sqlSession.selectList("eSQL.nameList", eVO);
	}
}
