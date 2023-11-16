package com.mycom.joytrip.tour.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.joytrip.tour.dto.TourDetailResponseDto;

import com.mycom.joytrip.tour.dto.TourResponseDto;

@Mapper
public interface TourDao {
	List<TourResponseDto> searchTourbyWord(String searchWord);
	
	List<TourResponseDto> searchTourbyWordAndOrder(@Param("searchWord")String searchWord, @Param("order") String order);
	
	List<TourResponseDto> orderTourList(String order);
	
	List<TourResponseDto> tourList();
	
	TourDetailResponseDto tourDetail(int contentId);
	
	List<TourResponseDto> tourRecommendList();
	
}
