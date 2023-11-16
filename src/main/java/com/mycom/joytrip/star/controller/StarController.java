package com.mycom.joytrip.star.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.joytrip.star.dto.StarRequestDto;
import com.mycom.joytrip.star.dto.StarResponseDto;
import com.mycom.joytrip.star.service.StarService;
import com.mycom.joytrip.user.dto.UserDto;

@RestController
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class StarController {
	
	@Autowired
	StarService starService;
	
	@PostMapping("/tours/stars")
	public ResponseEntity<Object> registTourStar(@RequestBody StarRequestDto starDto, HttpSession session) {
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		starDto.setUserId(userDto.getUserId());
		starService.registStar(starDto);
		
		return ResponseEntity.status(200).body("즐겨찾기 등록이 완료되었습니다");
	}
	
	@GetMapping("/tours/stars")
	public ResponseEntity<Object> retriveMyStarList(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		List<StarResponseDto> list = starService.retrieveStarList(userDto.getUserId());
		return ResponseEntity.status(200).body(list);
	}
	
	@DeleteMapping("/tours/stars/{contentId}")
	public ResponseEntity<Object> deleteMyStar(@PathVariable int contentId, HttpSession session) {
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
		starService.deleteStar(1, contentId);
		return ResponseEntity.status(200).body("즐겨찾기 삭제가 완료되었습니다.");
	}
	

	
}










