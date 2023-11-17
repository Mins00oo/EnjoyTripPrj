package com.mycom.joytrip.board.dto;

import java.util.List;

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
public class BoardResultDto {
	private int result;
	
	private BoardResponseDto dto; // 상세 1건 조회
	
	private List<BoardResponseDto> list; // 목록 조회
	
	private int count; // 총건수
}