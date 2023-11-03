package com.mycom.joytrip.tour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.tour.dao.TourDao;
import com.mycom.joytrip.tour.dto.TourResponseDto;

@Service
public class TourServiceImpl implements TourService {
	
	@Autowired
	TourDao tourDao;

	@Override
	public List<TourResponseDto> searchTourbyWord(String searchWord) {
		return tourDao.searchTourbyWord(searchWord);
	}

	@Override
	public List<TourResponseDto> orderTourList(String order) {
		return tourDao.orderTourList(order);
	}

	@Override
	public List<TourResponseDto> tourList() {
		return tourDao.tourList();
	}

	@Override
	public List<TourResponseDto> searchTourbyWordAndOrder(String searchWord, String order) {
		return tourDao.searchTourbyWordAndOrder(searchWord, order);
	}
	
	
}