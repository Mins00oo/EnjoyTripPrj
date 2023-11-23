package com.mycom.joytrip.star.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.joytrip.star.dto.StarRequestDto;
import com.mycom.joytrip.star.dto.StarResponseDto;

@Mapper
public interface StarDao {
	int registStar(StarRequestDto starDto);
	
	List<StarResponseDto> retrieveStarList(int userId);
	
	int deleteStar(@Param("userId") int userId, @Param("contentId") int contentId);

	StarResponseDto retriveMyStar(@Param("userId") int userId, @Param("contentId") int contentId);
	
	int myStarCount(int userId);
}
