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
import com.mycom.joytrip.tour.dto.TourResponseDto;

@Service
public class TourServiceImpl implements TourService {
	
	@Autowired
	TourDao tourDao;

	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	StarDao stardDao;


	@Override
	public List<TourResponseDto> searchTourbyWord(String searchWord) {
		return tourDao.searchTourbyWord(searchWord);
	}

	@Override
	public List<TourResponseDto> orderTourList(String order) {
		return tourDao.orderTourList(order);
	}

	@Override
	public List<TourResponseDto> tourList() {
		return tourDao.tourList();
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
	

}
