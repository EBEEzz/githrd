package com.githrd.www.service;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.githrd.www.dao.*;
import com.githrd.www.vo.*;

@Service // service 클래스를 빈등록해주는 어노테이션
@Aspect
public class LoginLogService {
	private static Logger loginLog = LoggerFactory.getLogger("memberLog");
	private static Logger boardLog = LoggerFactory.getLogger("boardLog");
	private static Logger gBoardLog = LoggerFactory.getLogger("gBoardLog");
	private static Logger reBoardLog = LoggerFactory.getLogger("reBoardLog");
	
	@Autowired
	BoardDao bDao;

	/*
		@Pointcut : 처리기가 실행될 시점을 지정(요청처리함수)
		
		참고 : 패턴 지정형식
				execution(* 패키지경로.Member.**Proc(..)) 
				==> Member 클래스의 함수중 Proc로 끝나는 함수들 모두를 의미
		
		@After	: 요청 처리함수가 실행된 후에 실행
		@Before : 요청 처리함수가 실행되기 전에 실행
		@Arround : 실행전 + 실행후
		
	 */

	@Pointcut("execution(* com.githrd.www.controller.Member.loginProc(..))")
	public void recordLogin() {
		System.out.println("##########  aop start #############");
	}

	@After("recordLogin()")
	public boolean rec(JoinPoint join) {
		MemberVO mVO = (MemberVO) join.getArgs()[1];

		if(mVO.getCnt() == 1) {
			loginLog.info(mVO.getId() + " 님 로그인");
		}

		return true;
	}

	@Before("execution(* com.githrd.www.controller.Member.logout(..))")
	public void logoutSetData(JoinPoint join) {
		HttpSession session = (HttpSession) join.getArgs()[1];
		MemberVO mVO = (MemberVO) join.getArgs()[2];
		mVO.setId((String) session.getAttribute("SID"));
	}

	@After("execution(* com.githrd.www.controller.Member.logout(..))")
	public void logoutRecord(JoinPoint join) {
		MemberVO mVO = (MemberVO) join.getArgs()[2];
		String id = mVO.getId();
		String result = mVO.getResult();

		if(result != null && result.equals("OK")) {
			loginLog.info(id + " 님 로그아웃");
		}
	}

	@Before("execution(* com.githrd.www.controller.Board.boardWriteProc(..))")
	public void setBoardMnoData(JoinPoint join) {
		BoardVO bVO = (BoardVO) join.getArgs()[1];
		String id = bVO.getId();

		bVO.setMno(bDao.getMno(id));
	}

	@After("execution(* com.githrd.www.controller.Board.boardWriteProc(..))")
	public void boardLogWrite(JoinPoint join) {
		BoardVO bVO = (BoardVO) join.getArgs()[1];
		String result = bVO.getResult();
		String id = bVO.getId();
		int bno = bVO.getBno();
		if(result.contentEquals("OK")) {
			boardLog.info(id + " 회원님이 [ " + bno + " ] 번글을 작성했습니다.");
		}
	}

	// 글삭제 로그남기기...


}
