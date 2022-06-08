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
import com.githrd.www.util.*;

/**
 * 이 클래스는 댓글 게시판 관련 요청을 처리할 클래스
 * @author	AnEunbee
 * @since	2022.06.08
 * @version	v.1.0
 * 
 * 			작업이력)
 * 				2022.06.08	-	담당자 : 안은비
 * 									클래스 제작,
 * 									리스트 보기 요청 처리
 *
 */

@Controller
@RequestMapping("/reBoard")
public class ReBoard {
	@Autowired
	ReBoardDao rDao;
	
	
	//댓글 게시판 리스트 보기 요청
	@RequestMapping("/reBoardList.blp")
	public ModelAndView reBoardList(ModelAndView mv, PageUtil page, String msg) {
		// 할일
		// 데이터베이스에서 데이터 가져오고
		// PageUtil이 먼저 만들어져야하기 때문에
		// 총 게시글 수부터 가져온다.
		int total = rDao.getTotal();
		
		// PageUtil 을 셋팅해준다.
		page.setPage(total);
		
		// 리스트를 조회한다.
		List<BoardVO> list = rDao.getList(page);
		
		if(msg != null) {
			mv.addObject("MSG", msg);
		}
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.setViewName("reBoard/reBoardList");
		
		return mv;
	}
	
	@RequestMapping(path="/reBoardWrite.blp", method=RequestMethod.POST, params={"id", "nowPage"})
	public ModelAndView reBoardWrite(ModelAndView mv, String id, String nowPage) {
		BoardVO bVO = rDao.getWriterInfo(id);
		
		// 데이터 넘겨주고
		mv.addObject("DATA", bVO);
		// 뷰 정하고
		mv.setViewName("reBoard/reBoardWrite");
		// 반환하고
		return mv;
	}
	
	@RequestMapping("/writeProc.blp")
	public ModelAndView writeProc(ModelAndView mv, String nowPage, BoardVO bVO) {
		int cnt = rDao.addReBoard(bVO);
		String view = "/www/reBoard/reBoardList.blp";
		if(cnt == 0) {
			// 글 등록에 실패한경우 ==> 글쓰기로 돌려보낸다.
			view = "/www/reBoard/reBoardWrite.blp";
		} else {
			// 글 등록에 성공한 경우...
			nowPage = "1";
			mv.addObject("MSG", "게시글 등록에 성공했습니다.");
		}
		
		mv.addObject("VIEW", view);
		mv.addObject("NOWPAGE", nowPage);
		
		mv.setViewName("reBoard/redirect");
		return mv;
	}
}
