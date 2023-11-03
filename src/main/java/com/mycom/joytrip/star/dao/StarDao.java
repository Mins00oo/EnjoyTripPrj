package com.mycom.joytrip.star.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.joytrip.star.dto.StarRequestDto;
import com.mycom.joytrip.star.dto.StarResponseDto;

@Mapper
public interface StarDao {
	int registStar(StarRequestDto starDto);
	
	List<StarResponseDto> retrieveStarList(int userId);
	
	StarResponseDto retrieveStar(int contentId);
	
	int deleteStar(int contentId);
	
	StarResponseDto retriveMyStar(int userId, int contentId);
}
