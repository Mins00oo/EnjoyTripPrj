package com.mycom.joytrip.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewRequestDto {
	private int reviewId;
	
	private int userId;

	private int contentId;
	
	private String content;
	
	private int score;
}
