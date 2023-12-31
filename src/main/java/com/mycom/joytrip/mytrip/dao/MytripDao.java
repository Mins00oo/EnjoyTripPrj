package com.mycom.joytrip.mytrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.joytrip.mytrip.dto.MytripRequestDto;
import com.mycom.joytrip.mytrip.dto.MytripResponseDto;
import com.mycom.joytrip.mytrip.dto.MytripTourDto;

@Mapper
public interface MytripDao {
	String getTitle(int mytripId);							//공유를 위한 제목 조회
	List<MytripResponseDto> mytripList(int userId);			//사용자가 보유한 마이트립 리스트
	List<MytripResponseDto> mytripDetail(MytripRequestDto dto);		//마이트립에 담긴 관광지 리스트 
	int insert(MytripRequestDto dto);						//마이트립 생성
	int share(MytripRequestDto dto);						//다른 사용자에게 공유
	int insertTour(MytripTourDto dto);						//마이트립에 관광지 추가
	int update(MytripRequestDto dto);						//마이트립 제목 수정
	int checkOwner(MytripRequestDto dto);					//해당 마이트립에 대한 소유자 확인
	int delete(int mytripId);								//마이트립 삭제
	int deleteTour(MytripTourDto dto);						//마이트립 내 관광지 삭제
	int deleteAllTour(int mytripId);						//마이트립 내 관광지 모두 삭제
	int countMytrip(int userId);							//사용자가 가진 마이트립 수
	int countUser(int mytripId);							//마이트립에 대한 소유자 수
	int countTour(int mytripId);							//마이트립에 대한 관광지 개수
}
