package com.mycom.joytrip.user.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.common.exception.CustomInsertException;
import com.mycom.joytrip.user.dao.UserDao;
import com.mycom.joytrip.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{	
	
	@Autowired
	UserDao userDao;
	
	@Override
	public UserDto userLogin(UserDto userDto) {
		UserDto user = userDao.userLogin(userDto.getUserEmail());
		if (user != null && BCrypt.checkpw(userDto.getUserPassword(), user.getUserPassword())) {
			user.setUserPassword(null);
			return user;
		}
		return null;
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
		
		UserDto detailUser = userDao.detailByEmail(dto.getUserEmail());
		// 기존에 존재하는 회원이라면
		if (detailUser != null) {
			throw new CustomInsertException("이미 존재하는 이메일입니다!");
		}
		
		String hashpw = BCrypt.hashpw(dto.getUserPassword(), BCrypt.gensalt());
        dto.setUserPassword(hashpw);
        System.out.println("hashpw" + hashpw);
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

	@Override
	public UserDto detailByEmail(String userEmail) {
		return userDao.detailByEmail(userEmail);
	}

	@Override
	public int updateUserPw(UserDto userDto) {
		String hashpw = BCrypt.hashpw(userDto.getUserPassword(), BCrypt.gensalt());
		userDto.setUserPassword(hashpw);
		return userDao.updateUserPw(userDto);
	}

}
