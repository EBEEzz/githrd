package com.githrd.www.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.githrd.www.dao.*;
import com.githrd.www.util.*;
import com.githrd.www.vo.BoardVO;

@Controller
@RequestMapping("/gBoard")
public class GuestBoard {
	@Autowired(required=false)
	GBoardDao gDao;
	
	@RequestMapping("/gBoardList.blp")
	public ModelAndView gBoardList(ModelAndView mv, PageUtil page) {
//		System.out.println("******************** page.nowPage : " + page.getNowPage());
//		System.out.println("******************** page.pageRow : " + page.getPageRow());
		
		// 총게시글 수 조회
		int total = gDao.getTotal();
		page.setPage(page.getNowPage(), total);
		
		
		// 게시글 목록 조회
		List<BoardVO> list = gDao.getList(page);
		
		// 데이터 심고
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		
		// 뷰 부르고
		mv.setViewName("gBoard/gBoardList");
		
		return mv;
	}
}
