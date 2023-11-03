package com.mycom.joytrip.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	int userId;
	String userPassword;
	String userEmail;
	String userName;
	String userNickname;
}
