package com.githrd.www.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.githrd.www.dao.*;
import com.githrd.www.vo.*;

@Controller
@RequestMapping("/member")
public class Member {
	@Autowired
	MemberDao mDao;
	@Autowired
	GBoardDao gDao;
	
	@RequestMapping("/login.blp")
	public ModelAndView loginForm(ModelAndView mv, HttpSession session) {
		mv.setViewName("member/login");
		return mv;
	}
	@RequestMapping(path="/login.blp", params= {"vw", "nowPage"})
	public ModelAndView loginForm(ModelAndView mv, HttpSession session, String vw, String nowPage) {
		mv.setViewName("member/login");
		return mv;
	}
	/*
	public String loginForm(HttpSession session, HttpServletResponse resp) {
		
		return "member/login";
	}
	*/
	
	@RequestMapping(path="/loginProc.blp", method=RequestMethod.POST, params={"id", "pw"})
	public ModelAndView loginProc(MemberVO mVO, HttpSession session, ModelAndView mv, RedirectView rv) {
//		System.out.println("### 일반 사용자");
//		System.out.println("********** id : " + id);
//		System.out.println("********** pw : " + pw);
//		System.out.println("********** mVO.id : " + mVO.getId());
//		System.out.println("********** mVO.pw : " + mVO.getPw());
		
		int cnt = mDao.getLogin(mVO);
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			session.setAttribute("MSG_CHECK", "OK");
			int count = gDao.getMyCount(mVO.getId());
			session.setAttribute("CNT", count);
			
			if(count == 0) {
				rv.setUrl("/www/gBoard/gBoardList.blp");
			} else {
				rv.setUrl("/www/main.blp");
			}
		} else {
			rv.setUrl("/www/member/login.blp");
		}
		mv.setView(rv);
		
		return mv;
	}
	//댓글게시판에서 로그인 처리를 요청하는 경우 처리함수
	@RequestMapping(path="/loginProc.blp", method=RequestMethod.POST, params={"id", "pw", "vw", "nowPage"})
	public ModelAndView loginProc(MemberVO mVO, HttpSession session, ModelAndView mv, RedirectView rv, String vw, String nowPage) {
		int cnt = mDao.getLogin(mVO);
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());	//로그인 처리
			session.setAttribute("MSG_CHECK", "OK");
			int count = gDao.getMyCount(mVO.getId());
			session.setAttribute("CNT", count);
			
			rv.setUrl(vw + "?nowPage=" + nowPage);	//리다이렉트
		} else {
			rv.setUrl("/www/member/login.blp?vw=" + vw + "&nowPage=" + nowPage);
		}
		mv.setView(rv);
		
		return mv;
	}
	/*
	public ModelAndView loginProc(String id, String pw, MemberVO mVO, HttpSession session, ModelAndView mv) {
//		System.out.println("### 일반 사용자");
//		System.out.println("************** id : " + id);
//		System.out.println("************** pw : " + pw);
//		System.out.println("************** mVO.id : " + mVO.getId());
//		System.out.println("************** mVO.pw : " + mVO.getPw());
		
		int cnt = mDao.getLogin(mVO);
		String view = "";
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			view = "redirect:../main.blp";
		} else {
			view = "redirect:login.blp";
		}
		mv.setViewName(view);
		return mv;
	}
	*/
	
	@RequestMapping(path="/loginProc.blp", params="id=admin")
	public ModelAndView adminProc(MemberVO mVO, HttpSession session, ModelAndView mv, RedirectView rv) {
//		System.out.println("### 관리자");
		
		int cnt = mDao.getLogin(mVO);
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			rv.setUrl("/www/main.blp");
		} else {
			rv.setUrl("/www/member/login.blp");
		}
		mv.setView(rv);
		return mv;
	}
	/*
	public ModelAndView adminLogin(MemberVO mVO, HttpSession session, ModelAndView mv) {
		System.out.println("### 관리자");
		
		int cnt = mDao.getLogin(mVO);
		String view = "";
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			view = "redirect:../main.blp";
		} else {
			view = "redirect:login.blp";
		}
		mv.setViewName(view);
		return mv;
	}
	*/
	
	@RequestMapping("/logout.blp")
	public ModelAndView logout(ModelAndView mv, HttpSession session, String vw, String nowPage) {
		session.removeAttribute("SID");
		
		if(vw == null) {
			vw = "/www/";
		}
		
		if(nowPage != null) {
			mv.addObject("NOWPAGE", nowPage);
		}
		
		mv.addObject("VIEW", vw);
		
		mv.setViewName("member/redirect");
		return mv;
	}
	
	@RequestMapping(path="/idCheck.blp",
					method=RequestMethod.POST, params="id")
	@ResponseBody
	public Map<String, String> idCheck(String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		
		int cnt = mDao.getIdCnt(id);
		
		if(cnt == 0) {
			result = "OK";
		}
		
		map.put("result", result);
		return map;
	}
	
	//회원가입 폼보기 요청
	@RequestMapping("/join.blp")
	public ModelAndView joinForm(ModelAndView mv, RedirectView rv) {
		
		List<MemberVO> list = mDao.getAvtList();
		
		//데이터 심고
		mv.addObject("LIST", list);
		mv.setViewName("member/join");
		return mv;
	}
	
	@RequestMapping(path="/joinProc.blp", method=RequestMethod.POST)
	public ModelAndView joinProc(MemberVO mVO, ModelAndView mv,
									RedirectView rv, HttpSession session) {
//		System.out.println("########## before mno : " + mVO.getMno());
		int cnt = mDao.addMember(mVO);
//		System.out.println("########## after mno : " + mVO.getMno());
		if(cnt == 1) {
			// 성공한 경우
			session.setAttribute("SID", mVO.getId());
			session.setAttribute("MSG_CHECK", "OK");
			int count = gDao.getMyCount(mVO.getId());
			session.setAttribute("CNT", count);
			
			if(count == 0) {
				rv.setUrl("/www/gBoard/gBoardList.blp");
			} else {
				rv.setUrl("/www/main.blp");
			}
		} else {
			rv.setUrl("/www/member/join.blp");
		}
		
		mv.setView(rv);
		
		return mv;
	}
	//댓글게시판에서 회원가입 처리요청 처리함수
	@RequestMapping(path="/joinProc.blp", method=RequestMethod.POST, params= {"vw", "nowPage"})
	public ModelAndView joinProc(MemberVO mVO, ModelAndView mv, HttpSession session,
			RedirectView rv, String vw, String nowPage) {
		int cnt = mDao.addMember(mVO);
		String view = vw;
		if(cnt == 1) {
			// 성공한 경우
			session.setAttribute("SID", mVO.getId());
			session.setAttribute("MSG_CHECK", "OK");
			int count = gDao.getMyCount(mVO.getId());
			session.setAttribute("CNT", count);
			
//			rv.setUrl(vw + "?nowPage=" + nowPage);	//get방식 처리
		} else {
//			rv.setUrl("/www/member/join.blp?vw=" + vw + "&nowPage=" + nowPage);	//get방식 처리
			view = "/www/member/join.blp";
		}
		
//		mv.setView(rv);	//get방식 처리
		mv.addObject("VIEW", view);
		mv.addObject("NOWPAGE", nowPage);
		mv.setViewName("reBoard/redirect");
		return mv;
	}
	
	@RequestMapping("/myInfo.blp")
	public ModelAndView myInfo(ModelAndView mv, String id) {
		//데이터 가져오고
		MemberVO mVO = mDao.getIdInfo(id);
		//뷰에 데이터 심고
		mv.addObject("DATA", mVO);
		//뷰 정하고
		mv.setViewName("member/memberInfo");
		
		return mv;
	}
	
	@RequestMapping("/memberInfo.blp")
	public ModelAndView memberInfo(ModelAndView mv, int mno) {
		MemberVO mVO = mDao.getMnoInfo(mno);
		
		mv.addObject("DATA", mVO);
		
		mv.setViewName("member/memberInfo");
		
		return mv;
	}
	
	@RequestMapping("/memberList.blp")
	public ModelAndView memberList(ModelAndView mv) {
		List<MemberVO> list = mDao.membList();
		
		mv.addObject("LIST", list);
		mv.setViewName("member/memberList");
		
		return mv;
	}
	
	@RequestMapping("/delMember.blp")
	public ModelAndView delMember(ModelAndView mv, String id, RedirectView rv, HttpSession session) {
		String sid = (String)session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/www/member/login.blp");
			mv.setView(rv);
			return mv;
		}
		
		if(!id.equals(sid)) {
			rv.setUrl("/www/member/myInfo.blp");
			mv.setView(rv);
			return mv;
		}
		
		int cnt = mDao.delMember(id);
		
		if(cnt == 1) {
			//세션에 기억시켜둔 데이터 삭제
			session.removeAttribute("SID");
			rv.setUrl("/www/");
		} else {
			rv.setUrl("/www/member/myInfo.blp");
		}
		
		mv.setView(rv);
		return mv;
	}
	
	//회원정보 수정 폼보기 요청 처리함수
	@RequestMapping("/myInfoEdit.blp")
	public ModelAndView myInfoEdit(ModelAndView mv, String id, HttpSession session, RedirectView rv) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/www/member/login.blp");
			mv.setView(rv);
			return mv;
		}
		
		if(!id.equals(sid)) {
			rv.setUrl("/www/main.blp");
			mv.setView(rv);
			return mv;
		}
		
		//데이터베이스 조회
		MemberVO mVO = mDao.getIdInfo(id);
		List<MemberVO> list = mDao.getAvtList(id);
		
		mv.addObject("DATA", mVO);
		mv.addObject("LIST",list);
		
		mv.setViewName("/member/editInfo");
		
		return mv;
	}
	
	//내정보 수정 처리요청 처리 함수
	@RequestMapping("/infoEditProc.blp")
	public ModelAndView infoEditProc(ModelAndView mv, MemberVO mVO, RedirectView rv) {
		int cnt = mDao.editMyInfo(mVO);
		String view = "member/redirect";
		if(cnt == 0) {
			mv.addObject("VIEW", "/www/member/myInfoEdit.blp");
		} else {
			mv.addObject("VIEW", "/www/member/myInfo.blp");
		}
		
		mv.setViewName(view);
		return mv;
	}
}
