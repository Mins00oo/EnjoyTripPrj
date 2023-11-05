package com.mycom.joytrip.review.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.joytrip.review.dto.ReviewRequestDto;
import com.mycom.joytrip.review.dto.ReviewResponseDto;
import com.mycom.joytrip.review.service.ReviewService;
import com.mycom.joytrip.user.dto.UserDto;

@RestController
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/tours/reviews")
	public ResponseEntity<Object> tourReviewRegist(@RequestBody ReviewRequestDto reviewRequestDto, HttpSession session) {
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		reviewRequestDto.setUserId(userDto.getUserId());
		System.out.println(reviewRequestDto);
		reviewService.registReview(reviewRequestDto);
		return ResponseEntity.status(200).body("후기 등록 완료되었습니다!");
	}
	
	@GetMapping("/tours/reviews")
	public ResponseEntity<Object> myTourReviewList(HttpSession session) {
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
		List<ReviewResponseDto> list = reviewService.retrieveMyReviewList(1);
		return ResponseEntity.status(200).body(list);
	}
	
	@DeleteMapping("/tours/reviews/{reviewId}")
	public ResponseEntity<Object> deleteTourReview(@PathVariable int reviewId) {
		System.out.println(reviewId);
		reviewService.deleteReview(reviewId);
		return ResponseEntity.status(200).body("후기가 삭제 되었씁니다!");
	}
	
	
}
