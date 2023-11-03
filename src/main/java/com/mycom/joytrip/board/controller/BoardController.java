package com.mycom.joytrip.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.joytrip.board.dto.BoardDto;
import com.mycom.joytrip.board.service.BoardService;

@RestController
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping(value="/boards")
	public List<BoardDto> list(){
		List<BoardDto> list = boardService.list();
		return list;
	}
	
	@GetMapping(value="/boards/{boardId}")
	//HttpSession session으로 어케 처리 해볼지 생각
	public BoardDto detail(@PathVariable int boardId){
		BoardDto dto = boardService.detail(boardId);
		return dto;
	}
	
	@PostMapping(value="/boards")
	public int insert(BoardDto dto){
		return boardService.insert(dto);
	}
	
	@PutMapping(value="/boards/{boardId}")
	public int update(@PathVariable int boardId, BoardDto dto){
		return boardService.update(dto);
	}
	
	@DeleteMapping(value="/boards/{boardId}")
	public int delete(@PathVariable int boardId){
		return boardService.delete(boardId);
	}
}
