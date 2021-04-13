package com.bitacademy.myportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
	//	요청을 받아야 한다
	//	url 패턴 /hello에 반응하고
	//	파라미터로 name을 받는다
	//	예: /hello?name=홍길동
	@RequestMapping("/main")
	public ModelAndView home() {
		//	Spring의 기본 응답 객체는 ModelAndView
		//	데이터(Model)와 뷰 객체의 정보를 함께 가지고 있다.
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/WEB-INF/views/home.jsp");
		
		return mav;
	}

}
//01/39/42  http://localhost:8080/hellospring/hello?name=Spring 라고 주소창에 치기 -->
//          http://localhost:8080/myportal/main