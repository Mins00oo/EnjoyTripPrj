package com.mycom.joytrip.star.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StarResponseDto {
	private int userId;
	  
	private int contentId;
	
	private String title;
	
	private String addr1;
	
	private String zipcode;
	
	private String firstImage;
	
	private String firstImage2;
}
