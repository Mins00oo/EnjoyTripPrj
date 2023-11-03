package com.mycom.joytrip.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.user.dao.UserDao;
import com.mycom.joytrip.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{	
	@Autowired
	UserDao userDao;
	
	@Override
	public UserDto userLogin(UserDto userDto) {
		return userDao.userLogin(userDto);
	}
	
	@Override
	public List<UserDto> list() {
		return userDao.list(); 
	}

	@Override
	public UserDto detail(int studentId) {
		return userDao.detail(studentId);
	}

	@Override
	public int insert(UserDto dto) {
		return userDao.insert(dto);
	}

	@Override
	public int update(UserDto dto) {
		return userDao.update(dto);
	}

	@Override
	public int delete(int studentId) {
		return userDao.delete(studentId);
	}

}
