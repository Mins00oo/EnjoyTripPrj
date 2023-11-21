package com.mycom.joytrip.tour.service;

import java.util.List;

import com.mycom.joytrip.review.dto.CheckResponseDto;
import com.mycom.joytrip.tour.dto.TourDetailResponseDto;
import com.mycom.joytrip.tour.dto.TourParamDto;
import com.mycom.joytrip.tour.dto.TourResponseDto;
import com.mycom.joytrip.tour.dto.TourResultDto;

public interface TourService {
	TourResultDto searchTourbyWord(TourParamDto tourParamDto);
	
	List<TourResponseDto> searchTourbyWordAndOrder(String searchWord, String order);
	
	List<TourResponseDto> orderTourList(String order);
	
	TourResultDto tourList(TourParamDto tourParamDto);

	TourDetailResponseDto tourDetail(int userId, int contentId);
	
	List<CheckResponseDto> retrieveUserCheckList(int userId);
	
	List<TourResponseDto> tourRecommendList();
	
	List<TourResponseDto> tourRelateList(int contentId);
	
	List<TourResponseDto> mainTourListByScore();
	
	TourResultDto tourRegionList(TourParamDto tourParamDto);
	
	TourResultDto tourListByCategory(TourParamDto tourParamDto);
	
	TourResultDto tourSidoList();
	
	TourResultDto tourGugunList(int sidoCode);
}
