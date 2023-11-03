package com.mycom.joytrip.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourResponseDto {
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

}
