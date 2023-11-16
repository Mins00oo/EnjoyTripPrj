package com.mycom.joytrip.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
	int userId;
	String userNickname;
	int boardId;
	String boardTitle;
	String boardDate;
	String boardContent;
}