package com.mycom.joytrip.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TestInterceptor implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("TestInterceptor >>> " + request.getRequestURI());
		
		//#1. 무조건 통과
//		return true;
		
		//#2. 선택적인 통과 <= 로그인 했으면 통과 <= 로그인 체크?? session에 "login":"success"
		//로그인 X "/login" 페이지 이동
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		if("success".equals(login)) return true;		//로그인 성공
		
		response.sendRedirect(request.getContextPath() + "/login");
		return false;		//로그인 실패
		
	}
	
}
