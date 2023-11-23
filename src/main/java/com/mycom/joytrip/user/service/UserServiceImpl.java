package com.mycom.joytrip.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.board.dao.BoardDao;
import com.mycom.joytrip.common.exception.CustomInsertException;
import com.mycom.joytrip.mytrip.dao.MytripDao;
import com.mycom.joytrip.review.dao.ReviewDao;
import com.mycom.joytrip.star.dao.StarDao;
import com.mycom.joytrip.user.dao.UserDao;
import com.mycom.joytrip.user.dto.UserDto;
import com.mycom.joytrip.user.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService{	
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	StarDao starDao;
	
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
		UserDto dto = userDao.detail(studentId);
		int visitedCount = reviewDao.myVisitedCount(studentId);
		int reviewCount = reviewDao.myReviewCount(studentId);
		int boardCount = boardDao.myBoardCount(studentId);
		int starCount = starDao.myStarCount(studentId);
		
		dto.setBoardCount(boardCount);
		dto.setReviewCount(reviewCount);
		dto.setVisitedCount(visitedCount);
		dto.setStarCount(starCount);
		
		return dto;
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
	public int updateUserPw(UserDto userDto, HttpSession session) {
		UserDto user = (UserDto) session.getAttribute("userDto");
		String hashpw = BCrypt.hashpw(userDto.getUserPassword(), BCrypt.gensalt());
		
		if (user != null) {
			if (BCrypt.checkpw(userDto.getUserPassword(), userDao.detail(user.getUserId()).getUserPassword())) {
				throw new CustomInsertException("기존과 동일한 비밀번호입니다!");
			}
			userDto.setUserPassword(hashpw);
			userDto.setUserId(user.getUserId());
			return userDao.updateUserPwAfterLogin(userDto);
		} 
		userDto.setUserPassword(hashpw);
		return userDao.updateUserPwBeforeLogin(userDto);
	}

	@Override
	public UserResultDto searchByNicknameOrEmail(String searchWord) {
		UserResultDto userResultDto = new UserResultDto();
		List<UserDto> list = userDao.searchByNicknameOrEmail(searchWord);
		userResultDto.setList(list);
		return userResultDto;
	}

}
