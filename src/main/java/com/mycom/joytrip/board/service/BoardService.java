package com.mycom.joytrip.board.service;

import java.util.List;

import com.mycom.joytrip.board.dto.BoardDto;

public interface BoardService {
	List<BoardDto> list();
	BoardDto detail(int studentId);
	int insert(BoardDto dto);
	int update(BoardDto dto);
	int delete(int studentId);
}
