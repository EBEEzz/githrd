package com.githrd.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Blp {

	@RequestMapping("/index.blp")
	public ModelAndView getIndex(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
}
