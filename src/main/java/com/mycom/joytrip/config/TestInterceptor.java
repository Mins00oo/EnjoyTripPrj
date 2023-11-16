package com.mycom.joytrip.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycom.joytrip.user.dto.UserDto;

@Component
public class TestInterceptor implements HandlerInterceptor{
	private final String jsonStr = "{\"result\" : \"login\"}";
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("TestInterceptor >>> " + request.getRequestURI());
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		System.out.println(request.getMethod());
		
		if (userDto == null) {
			if (request.getMethod().equals("POST") || request.getMethod().equals("OPTIONS")) {
				
				System.out.println("NO INterceptor");
				return true;
			}
			// 로그인 필요함
			response.getWriter().write(jsonStr);
			System.out.println("need login");
			return false;
		}
		
		return true;
		
	}
	
}
