package com.githrd.www.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.githrd.www.dao.*;
import com.githrd.www.vo.*;

@Controller
@RequestMapping("/arduino")
public class Arduino {
	@Autowired
	ArduDao aDao;

	// 데이터 추가 요청 처리함수
	@RequestMapping("/addData.blp")
	@ResponseBody
	public Map<String, String> addData(ArduVO aVO){
		HashMap<String, String> map = new HashMap<String, String>();

		int cnt = aDao.addData(aVO);

		String result = "OK";
		if(cnt != 1) {
			result = "NO";
		}

		map.put("result", result);

		return map;
	}

	@RequestMapping("/topFive.blp")
	public ModelAndView topFive(ModelAndView mv) {
		// 데이터 꺼내고
		List<ArduVO> list = aDao.getTopFive();

		// 데이터 심고
		mv.addObject("LIST", list);

		mv.setViewName("arduino/topFive");
		return mv;
	}
}
