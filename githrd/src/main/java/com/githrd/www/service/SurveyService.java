package com.githrd.www.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import com.githrd.www.dao.*;
import com.githrd.www.vo.*;

/**
 *  이 클래스는 설문조사에 관련된 부가적인 기능을 처리할 클래스
 * @author	전은석
 * @since	2022.06.13
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.06.13 	-	담당자 : 전은석
 * 								클래스제작
 *
 */


public class SurveyService {
	@Autowired
	SurveyDao sDao;
	
	// 설문주제 번호로 설문데이터 작업 셋팅 함수
	public void setBogi(SurveyVO sVO) {
		int sino = sVO.getSino();
		List<SurveyVO> list = sDao.getQuestList(sino); // 문항 리스트 꺼내기 작업
		
		sVO.setBogi(list);
		
		// 문항에 해당하는 보기 셋팅 작업
		for(SurveyVO vo : list) {
			int qno = vo.getSqno();
			List<SurveyVO> bogi = sDao.getBogiList(qno);
			
			vo.setBogi(bogi);
		}
	}
	
	// 문항과 보기를 동시에 조회해서 분리 처리하는 전담 함수
	public void settingList(SurveyVO sVO) {
		int sino = sVO.getSino();
		
		List<SurveyVO> qlist = sDao.getQList(sino);
		
		ArrayList<SurveyVO> munjae = new ArrayList<SurveyVO>();
		
		// 문항만 꺼내서 리스트 채우기
		for(SurveyVO vo : qlist) {
			if(vo.getSqno() == vo.getUpno()) {
				munjae.add(vo);
			}
		}
		
		// 보기만꺼내서 위의 문항리스트의 vo에 채우기
		for(SurveyVO vo : munjae) {
			ArrayList<SurveyVO> tmp = new ArrayList<SurveyVO>();
			for(SurveyVO bogi : qlist) {
				if(vo.getSqno() == bogi.getUpno() && vo.getSqno() != bogi.getSqno()) {
					tmp.add(bogi);
				}
			}
			vo.setBogi(tmp);
		}
		
		sVO.setBogi(munjae);
		
	}
}