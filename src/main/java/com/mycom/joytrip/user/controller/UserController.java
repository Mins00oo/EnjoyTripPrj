package com.mycom.joytrip.user.controller;

import java.util.HashMap;
import java.util.List;
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

import com.mycom.joytrip.user.dto.UserDto;
import com.mycom.joytrip.user.service.UserService;

//전체에 ResponseBody
@RestController
//CORS 에러를 해결하기 위함. (다른 서버에서 리소스를 공유하는 것에 대한 제한을 풀어주는 것)
//@CrossOrigin(origins="*", allowedHeaders="*")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping(value="/users")
	public List<UserDto> list(){
		List<UserDto> list = userService.list();
		return list;
	}
	
	@GetMapping(value="/users/{userId}")
	//HttpSession session으로 어케 처리 해볼지 생각
	public UserDto detail(@PathVariable int userId){
		UserDto dto = userService.detail(userId);
		return dto;
	}
	
	@GetMapping(value="/users/email/{userEmail}")
	//HttpSession session으로 어케 처리 해볼지 생각
	public Map<String, String> detailByEmail(@PathVariable String userEmail){
		UserDto dto = userService.detailByEmail(userEmail);
		Map<String, String> map = new HashMap<>();
		
		if (dto == null) {
			map.put("result", "fail");
			return map;
		}
		map.put("result", "success");
		return map;
	}
	
	@PostMapping(value="/users")
	public Map<String, String> insert(@RequestBody UserDto dto) {
		userService.insert(dto);
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");
		return map;
	}
	
	@PutMapping(value="/users/{userId}")
	public int update(@PathVariable int userId, @RequestBody UserDto dto){
		dto.setUserId(userId);
		
		return userService.update(dto);
	}
	
	@PutMapping(value="/users/pw")
	public Map<String, String> changeUserPw(@RequestBody UserDto userDto, HttpSession session){
		Map<String, String> map = new HashMap<>();
		userService.updateUserPw(userDto, session);
		map.put("result", "success");
		return map;
	}
	
	@DeleteMapping(value="/users/{userId}")
	public int delete(@PathVariable int userId){
		return userService.delete(userId);
	}
}
