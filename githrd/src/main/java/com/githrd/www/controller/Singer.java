package com.githrd.www.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("/singer")
public class Singer {

	@RequestMapping("/singer.blp")
	public ModelAndView singer(ModelAndView mv) {
		mv.setViewName("singer/singer");
		return mv;
	}

}
