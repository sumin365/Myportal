package com.bitacademy.myportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitacademy.myportal.exception.ControllerException;

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
		
		mav.setViewName("home");
		//	전달된 ViewName을 기반으로 ViewResolver에게 실제 뷰
		//	위치를 질의
		return mav;
	}
	
	//03/14-18/19
    //  /context + /url  ->  /myportal/except	
//	예외 처리 테스트
	@RequestMapping("/except")	
	@ResponseBody
	public String except(HttpServletRequest req) {
		try {
			int result = 4/0;	//	-> 예외
		} catch (Exception e) {
			//	예외는 완벽하게 복구 되기 힘들기 때문에
			//	예외를 전환해서 외부로 던진다
//			System.err.println("예외:" + e.getMessage());
//			throw new RuntimeException("Main Controller Error");
			//	일반적인 예외는 구체적인 의미를 가진 예외로 전환해서
			//	처리하는 것을 권장
			throw new ControllerException("Main Controller Error", req);
		}
		return "Exception Test";
	}
	
	//	ExceptionHandler v1
	//	컨트롤러 내부의 예외 처리
	//	내부에서 발생한 RuntimeException 예외를 처리하는 메서드
//	@ExceptionHandler(RuntimeException.class)
//	@ResponseBody
//	public String handleControllerExcept(RuntimeException e) {
//		return "Main Controller Exception:" + e.getMessage();
//	}
	
	//	ExceptionHandler v2 
	//	보다 구체적인 예외의 처리
//	@ExceptionHandler(ControllerException.class)
//	public String handleControllerExcept(ControllerException e, 
//			Model model) {
//		model.addAttribute("name", e.getClass().getSimpleName());
//		model.addAttribute("message", e.getMessage());
//		
//		//	예외 발생시 가급적
//		//	로그를 기록하고, 개발자나 관리자에게 고지
////		에러 발생 당시의 요청 정보를 기록
//		System.err.println("발생 예외:" + e.getClass().getSimpleName());
//		System.err.println("예외발생시 요청 URL:" + e.getReq().getRequestURI());	
//		
//		return "error/exception";
//	}
	
}
	
	
	
	
	
	
	
	//prefix surfix??????     error/exception 파일로 넘어가라

//01/39/42  http://localhost:8080/hellospring/hello?name=Spring 라고 주소창에 치기 -->
//          http://localhost:8080/myportal/main