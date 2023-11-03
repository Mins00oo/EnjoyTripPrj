package com.mycom.joytrip.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;

@Mapper
public interface BoardDao {
	List<BoardResponseDto> list();
	BoardResponseDto detail(int boardId);
	int insert(BoardRequestDto dto);
	int update(BoardRequestDto dto);
	int delete(int boardId);
}
