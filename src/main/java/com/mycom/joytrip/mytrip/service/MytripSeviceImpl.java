package com.mycom.joytrip.mytrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.joytrip.mytrip.dao.MytripDao;
import com.mycom.joytrip.mytrip.dto.MytripRequestDto;
import com.mycom.joytrip.mytrip.dto.MytripResponseDto;
import com.mycom.joytrip.mytrip.dto.MytripTourDto;

@Service
public class MytripSeviceImpl implements MytripService{
	@Autowired
	MytripDao dao;

	@Override
	public List<MytripResponseDto> mytripList(int userId) {
		return dao.mytripList(userId); 
	}

	@Override
	public List<MytripResponseDto> mytripDetail(int mytripId) {
		return dao.mytripDetail(mytripId);
	}

	@Override
	public int insert(MytripRequestDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int share(MytripRequestDto dto) {
		String title = dao.getTitle(dto.getMytripId());
		dto.setTitle(title);
		return dao.share(dto);
	}

	@Override
	public int insertTour(MytripTourDto dto) {
		return dao.insertTour(dto);
	}

	@Override
	public int update(MytripRequestDto dto) {
		return dao.update(dto);
	}
	
	@Override
	@Transactional
	public ResponseEntity<Object> delete(MytripRequestDto dto) {
		
		//3가지 과정 다 이뤄지도록
		//owner 확인
		int ownerId = dao.checkOwner(dto);
		//맞다면 삭제
		if(ownerId == 1) {
			try {
				dao.deleteAllTour(dto.getMytripId());
				dao.delete(dto.getMytripId());
			}
			catch(Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(200).body("마이트립 삭제 중 문제가 발생했습니다!");
	        }
		}
		//아니라면 기각
		else {
			return ResponseEntity.status(200).body("사용자가 해당 마이트립의 소유자가 아닙니다!");
		}
		
		return ResponseEntity.status(200).body("마이트립 삭제 완료!");
	}

	@Override
	public int deleteTour(MytripTourDto dto) {
		return dao.deleteTour(dto);
	}

	@Override
	public int countMytrip(int userId) {
		return dao.countMytrip(userId);
	}
	
	@Override
	public int countUser(int mytripId) {
		return dao.countUser(mytripId);
	}

	@Override
	public int countTour(int mytripId) {
		return dao.countTour(mytripId);
	}

}
