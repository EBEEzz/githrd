package com.githrd.www.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	
	@RequestMapping({"/", "/main.blp"})
	public String getMain() {
		return "main";
	}
	
	@RequestMapping("/mainMsgCheck.blp")
	@ResponseBody
	public Map<String, String> msgCheck(HttpSession session){
		HashMap<String, String> map = new HashMap<String, String>();
		
		session.setAttribute("MSG_CHECK", "NO");
		
		map.put("result", "OK");
		return map;
	}
}