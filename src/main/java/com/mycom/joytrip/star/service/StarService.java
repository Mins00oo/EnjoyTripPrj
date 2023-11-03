package com.mycom.joytrip.star.service;

import java.util.List;

import com.mycom.joytrip.star.dto.StarRequestDto;
import com.mycom.joytrip.star.dto.StarResponseDto;

public interface StarService {
	int registStar(StarRequestDto starDto);
	
	List<StarResponseDto> retrieveStarList(int userId);
	
	StarResponseDto retrieveStar(int contentId);
}
