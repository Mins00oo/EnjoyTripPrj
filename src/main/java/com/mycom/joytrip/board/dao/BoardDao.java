package com.mycom.joytrip.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.joytrip.board.dto.BoardFileDto;
import com.mycom.joytrip.board.dto.BoardParamDto;
import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;

@Mapper
public interface BoardDao {
	List<BoardResponseDto> boardList(BoardParamDto boardParamDto);
	BoardResponseDto detail(int boardId);
	int insert(BoardRequestDto dto);
	int boardFileInsert(BoardFileDto boardFileDto);
	int update(BoardRequestDto dto);
	int delete(int boardId);
	List<BoardResponseDto> boardListSearchWord(BoardParamDto boardParamDto);
	int boardListTotalCount();
	int boardListSearchWordTotalCount(BoardParamDto boardParamDto);
	int myBoardCount(int userId);
}    
   