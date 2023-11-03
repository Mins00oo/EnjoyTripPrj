package com.mycom.joytrip.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	int boardId;
	String boardTitle;
	int userId;
	String boardDate;
	String boardContent;
}