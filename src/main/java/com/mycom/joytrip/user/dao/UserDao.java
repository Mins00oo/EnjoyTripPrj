package com.mycom.joytrip.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.joytrip.user.dto.UserDto;

@Mapper
public interface UserDao {
	UserDto userLogin(UserDto userDto);
	
	List<UserDto> list();
	UserDto detail(int studentId);
	int insert(UserDto dto);
	int update(UserDto dto);
	int delete(int studentId);
}
