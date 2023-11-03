package com.mycom.joytrip.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.joytrip.board.dto.BoardDto;

@Mapper
public interface BoardDao {
	List<BoardDto> list();
	BoardDto detail(int studentId);
	int insert(BoardDto dto);
	int update(BoardDto dto);
	int delete(int studentId);
}
