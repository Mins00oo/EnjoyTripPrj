package com.mycom.joytrip.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
	private int reviewId;
	
	private int userId;
	
	private String userNickname;
	
	private int contentId;
	
	private String title;
	
	private String addr1;
	
	private String firstImage;
	
	private String firstImage2;
	
	private double latitude;
	
	private double longitude;
	
	private String content;
	
	private int score;
	
	private String createAt;
	
	private String updateAt;
}
