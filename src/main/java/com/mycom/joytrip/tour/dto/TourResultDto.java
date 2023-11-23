package com.mycom.joytrip.tour.dto;

import java.util.List;

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
public class TourResultDto {
	private List<TourResponseDto> list;
	
	private int count;
	
	private List<TourSidoResponseDto> sidoList;
	
	private List<TourGugunResponseDto> gugunList;
	
}
