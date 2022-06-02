package com.githrd.www.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	
	@RequestMapping({"/", "/main.blp"})
	public String getMain() {
		return "main";
	}
}