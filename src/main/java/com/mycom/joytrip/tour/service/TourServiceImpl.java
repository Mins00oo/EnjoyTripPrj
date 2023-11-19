package com.mycom.joytrip.tour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.review.dao.ReviewDao;
import com.mycom.joytrip.review.dto.CheckResponseDto;
import com.mycom.joytrip.review.dto.ReviewResponseDto;
import com.mycom.joytrip.star.dao.StarDao;
import com.mycom.joytrip.star.dto.StarResponseDto;
import com.mycom.joytrip.tour.dao.TourDao;
import com.mycom.joytrip.tour.dto.TourDetailResponseDto;
import com.mycom.joytrip.tour.dto.TourParamDto;
import com.mycom.joytrip.tour.dto.TourResponseDto;
import com.mycom.joytrip.tour.dto.TourResultDto;

@Service
public class TourServiceImpl implements TourService {
	
	@Autowired
	TourDao tourDao;

	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	StarDao stardDao;


	@Override
	public TourResultDto searchTourbyWord(TourParamDto tourParamDto) {
		TourResultDto tourResultDto = new TourResultDto();
		int count = tourDao.tourListSearchWordTotalCount(tourParamDto);
		
		List<TourResponseDto> list = tourDao.searchTourbyWord(tourParamDto);
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		return tourResultDto;
	}

	@Override
	public List<TourResponseDto> orderTourList(String order) {
		return tourDao.orderTourList(order);
	}

	@Override
	public TourResultDto tourList(TourParamDto tourParamDto) {
		TourResultDto tourResultDto = new TourResultDto();
		int count = tourDao.tourListTotalCount();
		List<TourResponseDto> list = tourDao.tourList(tourParamDto);
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		
		return tourResultDto;
	}

	@Override
	public List<TourResponseDto> searchTourbyWordAndOrder(String searchWord, String order) {
		return tourDao.searchTourbyWordAndOrder(searchWord, order);
	}

	@Override
	public TourDetailResponseDto tourDetail(int userId, int contentId) {
		TourDetailResponseDto tourDetail = tourDao.tourDetail(contentId);
		
		List<ReviewResponseDto> reviewResponseDtos = reviewDao.retriveContentReviewList(contentId);
		tourDetail.setReviewResponseDtos(reviewResponseDtos);
		
		StarResponseDto isFavorite = stardDao.retriveMyStar(userId, contentId);
		if (isFavorite == null) {
			tourDetail.setFavorite(false);
		} else {
			tourDetail.setFavorite(true);
		}
		return tourDetail;
	}

	@Override
	public List<CheckResponseDto> retrieveUserCheckList(int userId) {
		return reviewDao.retrieveUserCheckList(userId);
	}

	@Override
	public List<TourResponseDto> tourRecommendList() {
		return tourDao.tourRecommendList();
	}

	@Override
	public List<TourResponseDto> tourRelateList(int contentId) {
		TourDetailResponseDto tourDetail = tourDao.tourDetail(contentId);
		int sidoCode = tourDetail.getSidoCode();
		return tourDao.tourRelateList(sidoCode);
	}

	@Override
	public List<TourResponseDto> mainTourListByScore() {
		return tourDao.mainTourListByScore();
	}

	@Override
	public TourResultDto tourRegionList(TourParamDto tourParamDto) {
		TourResultDto tourResultDto = new TourResultDto();
		List<TourResponseDto> list = tourDao.tourRegionList(tourParamDto);
		int count = tourDao.tourRegionListCount(tourParamDto.getRegion());
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		
		return tourResultDto;
	}
	

}
