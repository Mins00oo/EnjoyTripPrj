package com.mycom.joytrip.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.review.dao.ReviewDao;
import com.mycom.joytrip.review.dto.ReviewRequestDto;
import com.mycom.joytrip.review.dto.ReviewResponseDto;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewDao reviewDao;

	@Override
	public int registReview(ReviewRequestDto reviewRequestDto) {
		return reviewDao.registReview(reviewRequestDto);
	}

	@Override
	public List<ReviewResponseDto> retrieveMyReviewList(int userId) {
		return reviewDao.retrieveMyReviewList(userId);
	}

	@Override
	public int deleteReview(int userId, int contentId) {
		return reviewDao.deleteReview(userId, contentId);
	}
	
	
}
