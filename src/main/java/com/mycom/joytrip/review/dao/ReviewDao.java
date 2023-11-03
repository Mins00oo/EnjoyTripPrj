package com.mycom.joytrip.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.joytrip.review.dto.ReviewRequestDto;
import com.mycom.joytrip.review.dto.ReviewResponseDto;

@Mapper
public interface ReviewDao {
	int registReview(ReviewRequestDto reviewRequestDto);
	
	List<ReviewResponseDto> retrieveMyReviewList(int userId);
	
	List<ReviewResponseDto> retriveContentReviewList(int contentId);
	
	int deleteReview(@Param("userId") int userId, @Param("contentId") int contentId);
}
