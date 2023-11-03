package com.mycom.joytrip.board.service;

import java.util.List;

import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;

public interface BoardService {
	List<BoardResponseDto> list();
	BoardResponseDto detail(int boardId);
	int insert(BoardRequestDto dto);
	int update(BoardRequestDto dto);
	int delete(int boardId);
}
