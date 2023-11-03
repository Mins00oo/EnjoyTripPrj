package com.mycom.joytrip.star.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.star.dao.StarDao;
import com.mycom.joytrip.star.dto.StarRequestDto;
import com.mycom.joytrip.star.dto.StarResponseDto;

@Service
public class StarServiceImpl implements StarService {
	
	@Autowired
	StarDao starDao;

	@Override
	public int registStar(StarRequestDto starDto) {
		if (starDao.retrieveStar(starDto.getContentId()) != null) {
			
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
	
	
}
