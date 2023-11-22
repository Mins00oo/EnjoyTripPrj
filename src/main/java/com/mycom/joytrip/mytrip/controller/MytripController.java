package com.mycom.joytrip.mytrip.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		return service.mytripList(userDto.getUserId());
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
	
	@DeleteMapping(value="/mytrips/{mytripId}")
	ResponseEntity<Object> delete(@PathVariable int mytripId, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		MytripRequestDto dto = new MytripRequestDto();
		dto.setUserId(userDto.getUserId());
		dto.setMytripId(mytripId);
		return service.delete(dto);
	}
	
	@DeleteMapping(value="/mytrips/tour/{mytripId}/{contentId}")
	int deleteTour(@PathVariable int mytripId, @PathVariable int contentId) {
		MytripTourDto dto = new MytripTourDto();
		dto.setMytripId(mytripId);
		dto.setContentId(contentId);
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
	
	@GetMapping(value="/mytrips/countTour")
	int countTour(@RequestBody int mytripId) {
		return service.countTour(mytripId);
	}
}
