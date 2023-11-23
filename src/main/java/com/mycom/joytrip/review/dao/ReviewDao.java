package com.mycom.joytrip.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.joytrip.review.dto.CheckResponseDto;
import com.mycom.joytrip.review.dto.ReviewRequestDto;
import com.mycom.joytrip.review.dto.ReviewResponseDto;

@Mapper
public interface ReviewDao {
	int registReview(ReviewRequestDto reviewRequestDto);
	
	List<ReviewResponseDto> retrieveMyReviewList(int userId);
	
	List<ReviewResponseDto> retriveContentReviewList(int contentId);
	
	int deleteReview(int reviewId);
	
	CheckResponseDto retrieveUserCheck(@Param("userId") int userId, @Param("contentId") int contentId);
	
	List<CheckResponseDto> retrieveUserCheckList(int userId);
	
	ReviewResponseDto retriveMyTourReview(@Param("userId") int userId, @Param("contentId") int contentId);
	
	int updateReview(ReviewRequestDto reviewRequestDto);
	
	int tourReviewCount(int contentId);
	
	
}
