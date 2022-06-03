package com.githrd.resource;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
class Resrc {
	
	@RequestMapping({"/img/*", "/css/*", "/js/*"})
	public String getResource() {	
		String view = "";
		//	/img/avatar/img_avatar1.png
		//	=> img/avatar/img_avatar1
		
		//여기서 요청 내용에 따라 뷰처리
		return view;
	}
}
