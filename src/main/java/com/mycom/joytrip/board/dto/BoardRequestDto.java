package com.mycom.joytrip.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
	int userId;
	int boardId;
	String boardTitle;
	String boardContent;
}