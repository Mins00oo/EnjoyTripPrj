package com.mycom.joytrip.tour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.joytrip.tour.dto.TourDetailResponseDto;
import com.mycom.joytrip.tour.dto.TourResponseDto;
import com.mycom.joytrip.tour.service.TourService;

@RestController
public class TourController {
	
	@Autowired
	TourService tourService;
	
	@GetMapping("/tours/search")
	public ResponseEntity<Object> searchToursByWord(@RequestParam(value = "searchWord") String searchWord, @RequestParam(value = "order", required = false) String order) {
		List<TourResponseDto> list;
		if (order == null) {
			list = tourService.searchTourbyWord(searchWord);
		} else {
			list = tourService.searchTourbyWordAndOrder(searchWord, order);
		}
		return ResponseEntity.status(200).body(list);
	}
	
	@GetMapping("/tours/order")
	public ResponseEntity<Object> tourListByOrder(@RequestParam(value = "by", required = false) String order) {
		List<TourResponseDto> orderTourList = tourService.orderTourList(order);
		return ResponseEntity.status(200).body(orderTourList);
	}
	
	@GetMapping("/tours")
	public ResponseEntity<Object> tourList() {
		List<TourResponseDto> tourList = tourService.tourList();
		return ResponseEntity.status(200).body(tourList);
	}
	
	@GetMapping("/tours/{contentId}")
	public ResponseEntity<Object> tourDetail(@PathVariable int contentId) {
		TourDetailResponseDto tourDetail = tourService.tourDetail(contentId);
		return ResponseEntity.status(200).body(tourDetail);
	}

}
