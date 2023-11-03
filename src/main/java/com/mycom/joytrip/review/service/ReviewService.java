package com.mycom.joytrip.review.service;

import java.util.List;

import com.mycom.joytrip.review.dto.ReviewRequestDto;
import com.mycom.joytrip.review.dto.ReviewResponseDto;

public interface ReviewService {
	int registReview(ReviewRequestDto reviewRequestDto);
	
	List<ReviewResponseDto> retrieveMyReviewList(int userId);
	
	int deleteReview(int userId, int contentId);
}
