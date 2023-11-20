package com.mycom.joytrip.board.service;

import com.mycom.joytrip.board.dto.BoardParamDto;
import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;
import com.mycom.joytrip.board.dto.BoardResultDto;

public interface BoardService {
	BoardResultDto boardList(BoardParamDto boardParamDto);
	BoardResponseDto detail(int boardId);
	int boardInsert(BoardRequestDto boardDto);
	int update(BoardRequestDto dto);
	int delete(int boardId);
	BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
}
