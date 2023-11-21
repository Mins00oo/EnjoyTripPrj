package com.mycom.joytrip.mytrip.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.joytrip.mytrip.dto.MytripRequestDto;
import com.mycom.joytrip.mytrip.dto.MytripResponseDto;
import com.mycom.joytrip.mytrip.dto.MytripTourDto;
import com.mycom.joytrip.mytrip.service.MytripService;
import com.mycom.joytrip.user.dto.UserDto;

@RestController()
public class MytripController {
	@Autowired
	MytripService service;
	
	@GetMapping(value="/mytrips")
	List<MytripResponseDto> mytripList(HttpSession session){
		//test용
		return service.mytripList(3);
		
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		return service.mytripList(userDto.getUserId());
	}
	
	@GetMapping(value="/mytrips/{mytripId}")
	List<MytripResponseDto> mytripDetail(@PathVariable int mytripId){ 
		return service.mytripDetail(mytripId);
	}
	
	@PostMapping(value="/mytrips")
	int insert(@RequestBody MytripRequestDto dto) {
		return service.insert(dto);
	}
	
	@PostMapping(value="/mytrips/share")
	int share(@RequestBody MytripRequestDto dto) {
		return service.share(dto);
	}
	
	@PostMapping(value="/mytrips/tour")
	int insertTour(@RequestBody MytripTourDto dto) { 
		return service.insertTour(dto);
	}
	
	@PutMapping(value="/mytrips/tour")
	int update(@RequestBody MytripRequestDto dto) {
		return service.update(dto);
	}
	
	@DeleteMapping(value="/mytrips")
	ResponseEntity<Object> delete(@RequestBody MytripRequestDto dto) {
		return service.delete(dto);
	}
	
	@DeleteMapping(value="/mytrips/tour")
	int deleteTour(@RequestBody MytripTourDto dto) {	
		return service.deleteTour(dto);
	}
	
	@GetMapping(value="/mytrips/countMytrip")
	int countMytrip(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		return service.countMytrip(userDto.getUserId());
	}
	
	@GetMapping(value="/mytrips/countUser")
	int countUser(@RequestBody int mytripId) {
		return service.countUser(mytripId);
	}
}
