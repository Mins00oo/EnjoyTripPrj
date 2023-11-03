package com.mycom.joytrip.tour.service;

import java.util.List;

import com.mycom.joytrip.tour.dto.TourResponseDto;

public interface TourService {
	List<TourResponseDto> searchTourbyWord(String searchWord);
	
	List<TourResponseDto> searchTourbyWordAndOrder(String searchWord, String order);
	
	List<TourResponseDto> orderTourList(String order);
	
	List<TourResponseDto> tourList();
}
