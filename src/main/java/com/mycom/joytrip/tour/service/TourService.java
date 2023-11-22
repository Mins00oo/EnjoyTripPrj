package com.mycom.joytrip.tour.service;

import java.util.List;

import com.mycom.joytrip.review.dto.CheckResponseDto;
import com.mycom.joytrip.tour.dto.TourDetailResponseDto;
import com.mycom.joytrip.tour.dto.TourParamDto;
import com.mycom.joytrip.tour.dto.TourResponseDto;
import com.mycom.joytrip.tour.dto.TourResultDto;
import com.mycom.joytrip.user.dto.UserDto;

public interface TourService {
	TourResultDto searchTourbyWord(TourParamDto tourParamDto);
	
	List<TourResponseDto> searchTourbyWordAndOrder(String searchWord, String order);
	
	List<TourResponseDto> orderTourList(String order);
	
	TourResultDto tourList(TourParamDto tourParamDto, UserDto userDto);

	TourDetailResponseDto tourDetail(UserDto userDto, int contentId);
	
	List<CheckResponseDto> retrieveUserCheckList(int userId);
	
	List<TourResponseDto> tourRecommendList();
	
	TourResultDto tourRelateList(int contentId, UserDto userDto);
	
	List<TourResponseDto> mainTourListByScore(int userId);
	
	TourResultDto tourRegionList(TourParamDto tourParamDto, UserDto userDto);
	
	TourResultDto tourListByCategory(TourParamDto tourParamDto, UserDto userDto);
	
	TourResultDto tourSidoList();
	
	TourResultDto tourGugunList(int sidoCode);
	
	TourResultDto searchTourbyWordAndSido(TourParamDto tourParamDto, UserDto userDto);
	
	TourResultDto searchTourByWordAndSidoByCategory(TourParamDto tourParamDto, UserDto userDto);
}
