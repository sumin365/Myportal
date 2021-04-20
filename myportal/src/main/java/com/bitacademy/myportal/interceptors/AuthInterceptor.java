package com.bitacademy.myportal.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bitacademy.myportal.vo.MemberVo;

//	인증이 필요한 url 패턴에 인터셉터 적용, 필요시 로그인 창으로 전환
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		logger.debug("AuthInterceptor");
		//	세션 확인(로그인 체크)
		HttpSession session = request.getSession();
		MemberVo authUser = null;
		
		//	세션 객체 얻어오기
		if (session != null) {
			authUser = (MemberVo)session.getAttribute("authUser");
		}
		
		//	authUser가 null(로그인 안함)이면
		//	-> 로그인 창으로 redirect
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/members/login");
			return false;	//	요청을 뒤로 보내는 것을 중단
		}
		//	로그인한 사용자면
		return true;	//	요청 처리를 계속 진행
	}

}