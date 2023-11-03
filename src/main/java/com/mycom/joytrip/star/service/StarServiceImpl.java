package com.mycom.joytrip.star.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.common.exception.CustomInsertException;
import com.mycom.joytrip.star.dao.StarDao;
import com.mycom.joytrip.star.dto.StarRequestDto;
import com.mycom.joytrip.star.dto.StarResponseDto;

@Service
public class StarServiceImpl implements StarService {
	
	@Autowired
	StarDao starDao;

	@Override
	public int registStar(StarRequestDto starDto) {
		if (starDao.retriveMyStar(starDto.getUserId(), starDto.getContentId()) != null) {
			System.out.println("즐찾 실패" + starDto.toString());
			throw new CustomInsertException("이미 즐겨찾기한 관광지입니다!");
		}
		return starDao.registStar(starDto);
	}

	@Override
	public List<StarResponseDto> retrieveStarList(int userId) {
		List<StarResponseDto> retrieveStarList = starDao.retrieveStarList(userId);
		return retrieveStarList;
	}

	@Override
	public StarResponseDto retrieveStar(int contentId) {
		return starDao.retrieveStar(contentId);
	}

	@Override
	public int deleteStar(int userId, int contentId) {
		return starDao.deleteStar(userId, contentId);
	}

	@Override
	public StarResponseDto retriveMyStar(int userId, int contentId) {
		return starDao.retriveMyStar(userId, contentId);
	}
	
	
}
