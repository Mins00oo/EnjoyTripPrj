package com.mycom.joytrip.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.joytrip.board.dao.BoardDao;
import com.mycom.joytrip.board.dto.BoardParamDto;
import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;
import com.mycom.joytrip.board.dto.BoardResultDto;
import com.mycom.joytrip.common.exception.CustomInsertException;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;
	
	@Value("${app.fileupload.uploadPath}")
	String uploadPath;
	
	@Value("${app.fileupload.uploadFolder}")
	String uploadFolder;

	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			List<BoardResponseDto> list = boardDao.boardList(boardParamDto);
			int count = boardDao.boardListTotalCount();
			boardResultDto.setCount(count);
			boardResultDto.setList(list);
		} catch (Exception e) {
			throw new CustomInsertException("게시글 조회 중 문제가 생겼습니다.");
		}

		return boardResultDto;
	}

	@Override
	public BoardResponseDto detail(int boardId) {
		return boardDao.detail(boardId);
	}

	@Override
	public int update(BoardRequestDto dto) {
		return boardDao.update(dto);
	}
	
	@Override
	public int delete(int boardId) {
		return boardDao.delete(boardId);
	}

	@Override
	@Transactional
	public int boardInsert(BoardRequestDto boardDto) {
		System.out.println(boardDto);
		try {
			boardDao.insert(boardDto); 
		} catch (Exception e) {
			throw new CustomInsertException("게시글 작성 데이터가 문제가 있습니다");
		}
		
		return 1;
	}

	@Override
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			List<BoardResponseDto> list = boardDao.boardListSearchWord(boardParamDto);
			int count = boardDao.boardListSearchWordTotalCount(boardParamDto);
			boardResultDto.setList(list);
			boardResultDto.setCount(count);

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomInsertException("검색 조건에 맞는 조회에 실패하였습니다.");
		}

		return boardResultDto;
	}

	
}
