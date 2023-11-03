package com.mycom.joytrip.user.service;

import java.util.List;

import com.mycom.joytrip.user.dto.UserDto;

public interface UserService {
	UserDto userLogin(UserDto userDto);
	
	List<UserDto> list();
	UserDto detail(int studentId);
	int insert(UserDto dto);
	int update(UserDto dto);
	int delete(int studentId);
}
