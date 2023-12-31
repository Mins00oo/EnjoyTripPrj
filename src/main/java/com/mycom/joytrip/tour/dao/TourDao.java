package com.mycom.joytrip.tour.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.joytrip.tour.dto.TourDetailResponseDto;
import com.mycom.joytrip.tour.dto.TourGugunResponseDto;
import com.mycom.joytrip.tour.dto.TourParamDto;
import com.mycom.joytrip.tour.dto.TourResponseDto;
import com.mycom.joytrip.tour.dto.TourSidoResponseDto;

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
	
	List<TourResponseDto> tourListByCategory(TourParamDto tourParamDto);
	
	int tourListByCategoryCount(TourParamDto tourParamDto);
	
	// 정렬 dao 4개 만들고 service에 paramDto에 따라 분기처리해서 요청
	
	List<TourResponseDto> tourListByCategoryOrderByOption(TourParamDto tourParamDto);
	
	List<TourResponseDto> tourListOrderByOption(TourParamDto tourParamDto);
	
	List<TourResponseDto> tourRegionByCategoryList(TourParamDto tourParamDto);
	
	List<TourResponseDto> tourRegionByCategoryOrderByOptionList(TourParamDto tourParamDto);
	
	int tourListByRegionCategoryCount(TourParamDto tourParamDto);
	
	List<TourResponseDto> tourRegionOrderByOptionList(TourParamDto tourParamDto);
	
	List<TourSidoResponseDto> tourSidoList();
	
	List<TourGugunResponseDto> tourGugunList(int sidoCode);
	
	List<TourResponseDto> searchTourbyWordAndSido(TourParamDto tourParamDto);
	
	int searchTourByWordAndSidoCount(TourParamDto tourParamDto);
	
	List<TourResponseDto> searchTourByWordAndSidoByCategory(TourParamDto tourParamDto);
	
	int searchTourByWordAndSidoByCategoryCount(TourParamDto tourParamDto);
	
	List<TourResponseDto> searchTourByWordAndCategory(TourParamDto tourParamDto);
	
	List<TourResponseDto> searchAgainTourByWord(TourParamDto tourParamDto);

	List<TourResponseDto> searchAgainTourByWordAndCategory(TourParamDto tourParamDto);
	
	List<TourResponseDto> searchAgainTourbyWordAndSido(TourParamDto tourParamDto);
	
	int countSearchAgainTourbyWordAndSido(TourParamDto tourParamDto);
}
