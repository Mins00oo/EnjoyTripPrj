package com.mycom.joytrip.tour.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycom.joytrip.review.dto.CheckResponseDto;
import com.mycom.joytrip.tour.dto.TourDetailResponseDto;
import com.mycom.joytrip.tour.dto.TourParamDto;
import com.mycom.joytrip.tour.dto.TourResponseDto;
import com.mycom.joytrip.tour.dto.TourResultDto;
import com.mycom.joytrip.tour.service.TourService;
import com.mycom.joytrip.user.dto.UserDto;

@RestController
public class TourController {
	
	@Autowired
	TourService tourService;
	
	@GetMapping("/tours/order")
	public ResponseEntity<Object> tourListByOrder(@RequestParam(value = "by", required = false) String order) {
		List<TourResponseDto> orderTourList = tourService.orderTourList(order);
		return ResponseEntity.status(200).body(orderTourList);
	}
	
	@GetMapping("/tours")
	public ResponseEntity<Object> tourList(TourParamDto tourParamDto, HttpSession session) {
		System.out.println(tourParamDto);
		TourResultDto tourResultDto = new TourResultDto();
		
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		System.out.println("userDto = " + userDto);
		if (!tourParamDto.getSidoCode().isEmpty() ) {
			TourResultDto result = new TourResultDto();
			// 시도 + 구군으로 검색한 결과 조회
			if (!tourParamDto.getCategory().isEmpty() && !tourParamDto.getOption().isEmpty()) {
				// 카테고리 분류 + 정렬
				result = tourService.searchTourByWordAndSidoByCategory(tourParamDto, userDto);
				return ResponseEntity.status(200).body(result);
			} else if (!tourParamDto.getCategory().isEmpty()) {
				result = tourService.searchTourByWordAndSidoByCategory(tourParamDto, userDto);
				return ResponseEntity.status(200).body(result);
			} else {
				System.out.println("검색어 + 시군구");
				result = tourService.searchTourbyWordAndSido(tourParamDto, userDto);
				return ResponseEntity.status(200).body(result);
			}
		}
		
		if (!tourParamDto.getCategory().isEmpty() && tourParamDto.getRegion().isEmpty() && tourParamDto.getSearchWord().isEmpty()) {
			// 카테고리별 분류
			tourResultDto = tourService.tourListByCategory(tourParamDto, userDto);
			return ResponseEntity.status(200).body(tourResultDto);
		}
		
		if (!tourParamDto.getRegion().isEmpty()) {
			// 지역별 검색 => 카테고리 + 정렬 가능
			tourResultDto = tourService.tourRegionList(tourParamDto, userDto);
			return ResponseEntity.status(200).body(tourResultDto);
		} 
		
        if( tourParamDto.getSearchWord().isEmpty() ) {
        	tourResultDto = tourService.tourList(tourParamDto, userDto);
        }else {
        	System.out.println("그냥 검색어로만");
        	tourResultDto = tourService.searchTourbyWord(tourParamDto, userDto);
        }
		return ResponseEntity.status(200).body(tourResultDto);
	}
	
	@GetMapping("/tours/region/{region}")
	public ResponseEntity<Object> tourRegionList(@PathVariable String region, TourParamDto tourParamDto, HttpSession session) {
		TourResultDto tourResultDto;
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
        if( tourParamDto.getSearchWord().isEmpty() ) {
        	tourResultDto = tourService.tourList(tourParamDto, userDto);
        }else {
        	tourResultDto = tourService.searchTourbyWord(tourParamDto, userDto);
        }
        System.out.println(tourResultDto);
		return ResponseEntity.status(200).body(tourResultDto);
	}

	@GetMapping("/tours/{contentId}")
	public ResponseEntity<Object> tourDetail(@PathVariable int contentId, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		TourDetailResponseDto tourDetail = tourService.tourDetail(userDto, contentId);
		return ResponseEntity.status(200).body(tourDetail);
	}
	
	@GetMapping("/tours/users")
	public ResponseEntity<Object> userTourList(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		List<CheckResponseDto> userTourList = tourService.retrieveUserCheckList(userDto.getUserId());
		return ResponseEntity.status(200).body(userTourList);
	}
	
	@GetMapping("/tours/recommends")
	public ResponseEntity<Object> retriveTourRecommendList() {
		List<TourResponseDto> recommendList = tourService.tourRecommendList();
		return ResponseEntity.status(200).body(recommendList);
	}
	
	@GetMapping("/tours/relate/{contentId}")
	public ResponseEntity<Object> relateTourList(@PathVariable int contentId, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		TourResultDto list = tourService.tourRelateList(contentId, userDto);
		return ResponseEntity.status(200).body(list);
	}
	
	@GetMapping("/tours/main")
	public ResponseEntity<Object> mainTourList(HttpSession httpSession) {
		UserDto userDto = (UserDto) httpSession.getAttribute("userDto");
		List<TourResponseDto> mainTourRecommendList = new ArrayList<>();
		TourResultDto tourResultDto = new TourResultDto();
		if (userDto == null) {
			mainTourRecommendList = tourService.mainTourListByScore(0);
			tourResultDto.setList(mainTourRecommendList);
		} else {
			mainTourRecommendList = tourService.mainTourListByScore(userDto.getUserId());
			tourResultDto.setList(mainTourRecommendList);
		}
		return ResponseEntity.status(200).body(tourResultDto);
	}
	
	@GetMapping("/tours/sido")
	public ResponseEntity<Object> mainTourSidoList() {
		TourResultDto result = tourService.tourSidoList();
		return ResponseEntity.status(200).body(result);
	}
	
	@GetMapping("/tours/gugun/{sidoCode}")
	public ResponseEntity<Object> mainTourGugunList(@PathVariable int sidoCode) {
		TourResultDto result = tourService.tourGugunList(sidoCode);
		return ResponseEntity.status(200).body(result);
	}

}
