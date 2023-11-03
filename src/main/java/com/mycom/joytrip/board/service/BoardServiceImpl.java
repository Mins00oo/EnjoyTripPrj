package com.mycom.joytrip.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.board.dao.BoardDao;
import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardResponseDto> list() {
		return boardDao.list();
	}

	@Override
	public BoardResponseDto detail(int boardId) {
		return boardDao.detail(boardId);
	}

	@Override
	public int insert(BoardRequestDto dto) {
		return boardDao.insert(dto);
	}

	@Override
	public int update(BoardRequestDto dto) {
		return boardDao.update(dto);
	}
	
	@Override
	public int delete(int boardId) {
		return boardDao.delete(boardId);
	}
	
}
