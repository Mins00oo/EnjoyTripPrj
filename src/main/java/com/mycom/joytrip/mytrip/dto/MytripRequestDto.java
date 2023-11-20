package com.mycom.joytrip.mytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MytripRequestDto {
	int mytripId;
	int userId;
	int isOwner;
	String title;
}
