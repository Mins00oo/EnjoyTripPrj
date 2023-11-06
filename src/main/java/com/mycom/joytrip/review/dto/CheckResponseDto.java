package com.mycom.joytrip.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckResponseDto {
	private int visitedId;
	
	private int userId;
	
	private int contentId;
	
	private String userNickname;
	
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
}
