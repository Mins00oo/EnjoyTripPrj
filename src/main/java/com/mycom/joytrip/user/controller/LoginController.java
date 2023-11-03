package com.mycom.joytrip.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycom.joytrip.user.dto.UserDto;
import com.mycom.joytrip.user.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	/*
	 * 로그인 요청
	 * 성공시 home.jsp로 userDto를 담아서 이동
	 * 실패시 login.jsp로 재요청
	 */
	@PostMapping("/login")
	public String userLogin(UserDto userDto, HttpSession session) {
		System.out.println(userDto);
		UserDto user = userService.userLogin(userDto);
		System.out.println(user);

		if (user == null) {
			System.out.println("로그인 실패");
			return "login";
		}
		session.setAttribute("userDto", user);
		session.setAttribute("login", "success");
		return "redirect:/userMain.html";
	}

	
	/*
	 * 로그아웃 요청
	 * 로그아웃 후 메인화면으로 이동
	 */
	@GetMapping("/logout")
	public ModelAndView userLogout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView("redirect:/home.html");
		return mav;
	}
}
