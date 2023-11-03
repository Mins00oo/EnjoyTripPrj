package com.mycom.joytrip.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.board.dao.BoardDao;
import com.mycom.joytrip.board.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardDto> list() {
		return boardDao.list();
	}

	@Override
	public BoardDto detail(int studentId) {
		return boardDao.detail(studentId);
	}

	@Override
	public int insert(BoardDto dto) {
		return boardDao.insert(dto);
	}

	@Override
	public int update(BoardDto dto) {
		return boardDao.update(dto);
	}

	@Override
	public int delete(int studentId) {
		return boardDao.delete(studentId);
	}
	
	
}
