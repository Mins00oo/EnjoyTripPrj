package com.mycom.joytrip.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.joytrip.user.dto.UserDto;
import com.mycom.joytrip.user.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	UserService userService;
	
	/*
	 * 로그인 요청
	 * 성공시 home.jsp로 userDto를 담아서 이동
	 * 실패시 login.jsp로 재요청
	 */
	@PostMapping("/login")
	public Map<String, String> userLogin(@RequestBody UserDto userDto, HttpSession session) {
		UserDto user = userService.userLogin(userDto);
		Map<String, String> map = new HashMap<>();

		if (user == null) {
			System.out.println("로그인 실패");
			map.put("result", "fail");
			return map;
		}
		session.setAttribute("userDto", user);
		session.setAttribute("login", "success");
		map.put("result", "success");
		map.put("userId", Integer.toString(user.getUserId()));
		map.put("userNickName", user.getUserNickname());
		return map;
	}

	
	/*
	 * 로그아웃 요청
	 * 로그아웃 후 메인화면으로 이동
	 */
	@GetMapping("/logout")
	public Map<String, String> userLogout(HttpSession session) {
		session.invalidate();
		Map<String, String> map = new HashMap<>();
		
		map.put("result", "success");
		return map;
	}
}
