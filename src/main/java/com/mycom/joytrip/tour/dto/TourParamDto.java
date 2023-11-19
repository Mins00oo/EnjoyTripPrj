package com.mycom.joytrip.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TourParamDto {
	private int limit;
	
	private int offset;
	
	private String searchWord;
	
	private int contentId;
	
	private int userId;
	
	private String region;
}
