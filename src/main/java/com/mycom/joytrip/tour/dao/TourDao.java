package com.mycom.joytrip.tour.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.joytrip.tour.dto.TourDetailResponseDto;
import com.mycom.joytrip.tour.dto.TourParamDto;
import com.mycom.joytrip.tour.dto.TourResponseDto;

@Mapper
public interface TourDao {
	List<TourResponseDto> searchTourbyWord(TourParamDto tourParamDto);
	
	List<TourResponseDto> searchTourbyWordAndOrder(@Param("searchWord")String searchWord, @Param("order") String order);
	
	List<TourResponseDto> orderTourList(String order);
	
	List<TourResponseDto> tourList(TourParamDto tourParamDto);
	
	TourDetailResponseDto tourDetail(int contentId);
	
	List<TourResponseDto> tourRecommendList();
	
	List<TourResponseDto> tourRelateList(int sidoCode);
	
	List<TourResponseDto> mainTourListByScore();
	
	int tourListTotalCount();
	
	int tourListSearchWordTotalCount(TourParamDto tourParamDto);
	
	List<TourResponseDto> tourRegionList(TourParamDto tourParamDto);
	
	int tourRegionListCount(String region);
	
}
