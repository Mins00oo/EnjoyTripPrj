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
public class MytripResponseDto {
	int mytripId;
	int contentId;
	String title;
	int mytripCnt;
}
