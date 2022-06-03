package com.githrd.www.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.githrd.www.dao.*;

@Controller
@RequestMapping("/gboard")
public class GuestBoard {
	@Autowired(required=false)
	GBoardDao gDao;
	
	public ModelAndView getList(ModelAndView mv) {
		return mv;
	}
}
