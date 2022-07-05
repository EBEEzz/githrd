package com.githrd.www.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.githrd.www.vo.SingerVO;

public class SingerDao {
	@Autowired
	SqlSessionTemplate sqlSession;

	public List<SingerVO> singerList(SingerVO songVO){
		return sqlSession.selectList("singer.getSinger", songVO);
	}

	public List<SingerVO> memberList(int num){
		return sqlSession.selectList("singer.singerInfo", num);
	}

	public String getPhoto(int num){
		return sqlSession.selectOne("singer.getPhoto", num);
	}


}