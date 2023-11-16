package com.mycom.joytrip.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.common.exception.CustomInsertException;
import com.mycom.joytrip.review.dao.ReviewDao;
import com.mycom.joytrip.review.dto.CheckResponseDto;
import com.mycom.joytrip.review.dto.ReviewRequestDto;
import com.mycom.joytrip.review.dto.ReviewResponseDto;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewDao reviewDao;

	@Override
	public int registReview(ReviewRequestDto reviewRequestDto) {
		CheckResponseDto checkDto = reviewDao.retrieveUserCheck(reviewRequestDto.getUserId(), reviewRequestDto.getContentId());
		if (checkDto == null) {
			throw new CustomInsertException("다녀온 여행지가 아닙니다.");
		}
		
		ReviewResponseDto myTourReview = reviewDao.retriveMyTourReview(reviewRequestDto.getUserId(), reviewRequestDto.getContentId());
		if (myTourReview != null) {
			throw new CustomInsertException("이미 후기를 작성하셨습니다!");
		}
		
		reviewDao.registReview(reviewRequestDto);
		return 1;
	}

	@Override
	public List<ReviewResponseDto> retrieveMyReviewList(int userId) {
		return reviewDao.retrieveMyReviewList(userId);
	}

	@Override
	public int deleteReview(int reviewId) {
		return reviewDao.deleteReview(reviewId);
	}
	
	
}
