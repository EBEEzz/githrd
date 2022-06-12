package com.githrd.www.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.githrd.www.vo.*;

public class SurveyDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 참여하지 않은 설문 갯수 조회 전담 처리함수
	public int getCount(SurveyVO sVO) {
		return sqlSession.selectOne("sSQL.remainSurvey", sVO);
	}
	
	// 진행중인 설문 리스트 조회 전담 처리함수
	public List<SurveyVO> getIngList(String id){
		return sqlSession.selectList("sSQL.ingList", id);
	}
	
	// 종료된 설문 리스트 조회 전담 처리함수
	public List<SurveyVO> getOldList(){
		return sqlSession.selectList("sSQL.oldList");
	}
}