package com.mycom.joytrip.star.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.joytrip.star.dto.StarRequestDto;
import com.mycom.joytrip.star.dto.StarResponseDto;
import com.mycom.joytrip.star.service.StarService;

@RestController
@ControllerAdvice
public class StarController {
	
	@Autowired
	StarService starService;
	
	@PostMapping("/tours/stars")
	public ResponseEntity<Object> registTourStar(@RequestBody StarRequestDto starDto, HttpSession session) {
		Map<String, String> map = new HashMap<>();
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		starDto.setUserId(userDto.getUserId());
		System.out.println(starDto);
		starService.registStar(starDto);
		
		return ResponseEntity.status(200).body("즐겨찾기 등록이 완료되었습니다");
	}
	
	@GetMapping("/tours/stars")
	public ResponseEntity<Object> retrieveTourList(HttpSession session) {
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
		List<StarResponseDto> list = starService.retrieveStarList(1);
		return ResponseEntity.status(200).body(list);
	}
	
	@GetMapping("/tours/stars/{contentId}")
	public ResponseEntity<Object> retrieveTourDetail(@PathVariable int contentId) {
		StarResponseDto starResponseDto = starService.retrieveStar(contentId);
		return ResponseEntity.status(200).body(starResponseDto);
	}
	
	@DeleteMapping("/tours/stars/{contentId}")
	public ResponseEntity<Object> deleteTourDetail(@PathVariable int contentId, HttpSession session) {
		starService.deleteStar(1, contentId);
		return ResponseEntity.status(200).body("즐겨찾기 삭제가 완료되었습니다.");
	}
	

	
}










