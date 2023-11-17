package com.mycom.joytrip.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;
import com.mycom.joytrip.board.service.BoardService;
import com.mycom.joytrip.user.dto.UserDto;

@RestController
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping(value="/boards")
	public List<BoardResponseDto> list(){
		List<BoardResponseDto> list = boardService.list();
		return list;
	}
	
	@GetMapping(value="/boards/{boardId}")
	public BoardResponseDto detail(@PathVariable int boardId){
		BoardResponseDto dto = boardService.detail(boardId);
		return dto;
	}
	
	@PostMapping(value="/boards")
	public Map<String, String> insert(BoardRequestDto boardDto, MultipartHttpServletRequest request, HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		boardDto.setUserId(userDto.getUserId());
		boardService.boardInsert(boardDto, request);
		Map<String, String> map = new HashMap<>();
		
		map.put("result", "success");
		
		return map;
	}
	
	@PutMapping(value="/boards/{boardId}")
	public int update(@PathVariable int boardId, BoardRequestDto dto){
		return boardService.update(dto);
	}
	
	@DeleteMapping(value="/boards/{boardId}")
	public int delete(@PathVariable int boardId){
		return boardService.delete(boardId);
	}
}
