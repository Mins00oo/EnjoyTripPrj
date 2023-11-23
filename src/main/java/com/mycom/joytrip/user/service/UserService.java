package com.mycom.joytrip.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mycom.joytrip.user.dto.UserDto;
import com.mycom.joytrip.user.dto.UserResultDto;

public interface UserService {
	UserDto userLogin(UserDto userDto);
	
	List<UserDto> list();
	UserDto detail(int userId);
	int insert(UserDto dto);
	int update(UserDto dto);
	int delete(int userId);
	UserDto detailByEmail(String userEmail);
	int updateUserPw(UserDto userDto, HttpSession session);
	UserResultDto searchByNicknameOrEmail(String searchWord);
}
