package com.mycom.joytrip.tour.dto;

import java.util.List;

import com.mycom.joytrip.review.dto.ReviewResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourDetailResponseDto {
	private int contentId;
	
	private String title;
	
	private String addr1;
	
	private String zipcode;
	
	private String firstImage;
	
	private String firstImage2;
	
	private int readcount;
	
	private int sidoCode;
	
	private int gugunCode;
	
	private double latitude;
	
	private double longtitude;
	
	private double avgScore;
	
	private boolean favorite;
	
	List<ReviewResponseDto> reviewResponseDtos;
}
