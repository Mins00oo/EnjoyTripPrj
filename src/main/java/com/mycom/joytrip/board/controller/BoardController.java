package com.mycom.joytrip.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.joytrip.board.dto.BoardParamDto;
import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;
import com.mycom.joytrip.board.dto.BoardResultDto;
import com.mycom.joytrip.board.service.BoardService;
import com.mycom.joytrip.user.dto.UserDto;

@RestController
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping(value="/boards")
	public BoardResultDto list(BoardParamDto boardParamDto){
		BoardResultDto boardResultDto;
		
        if( boardParamDto.getSearchWord().isEmpty() ) {
        	boardResultDto = boardService.boardList(boardParamDto);
        }else {
        	boardResultDto = boardService.boardListSearchWord(boardParamDto);
        }
		return boardResultDto;
	}
	
	@GetMapping(value="/boards/{boardId}")
	public BoardResponseDto detail(@PathVariable int boardId){
		BoardResponseDto dto = boardService.detail(boardId);  
		return dto;
	}
	
	@PostMapping(value="/boards")
	public Map<String, String> insert(@RequestBody BoardRequestDto boardDto, HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		boardDto.setUserId(userDto.getUserId());
		boardService.boardInsert(boardDto);
		Map<String, String> map = new HashMap<>();
		
		map.put("result", "success");
		
		return map;
	}
	
	@PutMapping(value="/boards")
	public int update(@RequestBody BoardRequestDto dto, HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		dto.setUserId(userDto.getUserId());
		return boardService.update(dto);
	}
	
	@DeleteMapping(value="/boards/{boardId}")
	public int delete(@PathVariable int boardId){
		return boardService.delete(boardId);
	}
}
