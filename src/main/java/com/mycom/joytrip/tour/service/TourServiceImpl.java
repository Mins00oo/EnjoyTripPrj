package com.mycom.joytrip.tour.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.joytrip.review.dao.ReviewDao;
import com.mycom.joytrip.review.dto.CheckResponseDto;
import com.mycom.joytrip.review.dto.ReviewResponseDto;
import com.mycom.joytrip.star.dao.StarDao;
import com.mycom.joytrip.star.dto.StarResponseDto;
import com.mycom.joytrip.tour.dao.TourDao;
import com.mycom.joytrip.tour.dto.TourDetailResponseDto;
import com.mycom.joytrip.tour.dto.TourGugunResponseDto;
import com.mycom.joytrip.tour.dto.TourParamDto;
import com.mycom.joytrip.tour.dto.TourResponseDto;
import com.mycom.joytrip.tour.dto.TourResultDto;
import com.mycom.joytrip.tour.dto.TourSidoResponseDto;

@Service
public class TourServiceImpl implements TourService {
	
	@Autowired
	TourDao tourDao;

	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	StarDao stardDao;


	@Override
	public TourResultDto searchTourbyWord(TourParamDto tourParamDto) {
		TourResultDto tourResultDto = new TourResultDto();
		int count = tourDao.tourListSearchWordTotalCount(tourParamDto);
		
		List<TourResponseDto> list = tourDao.searchTourbyWord(tourParamDto);
		
		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);
		}
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		return tourResultDto;
	}

	@Override
	public List<TourResponseDto> orderTourList(String order) {
		return tourDao.orderTourList(order);
	}

	@Override
	public TourResultDto tourList(TourParamDto tourParamDto) {
		TourResultDto tourResultDto = new TourResultDto();
		int count = tourDao.tourListTotalCount();
		List<TourResponseDto> list = new ArrayList<>();
		
		if (tourParamDto.getOption().isEmpty()) {
			list = tourDao.tourList(tourParamDto);
		} else {
			list = tourDao.tourListOrderByOption(tourParamDto);
		}
		
		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);
		}
		
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		
		return tourResultDto;
	}

	@Override
	public List<TourResponseDto> searchTourbyWordAndOrder(String searchWord, String order) {
		return tourDao.searchTourbyWordAndOrder(searchWord, order);
	}

	@Override
	public TourDetailResponseDto tourDetail(int userId, int contentId) {
		TourDetailResponseDto tourDetail = tourDao.tourDetail(contentId);
		
		List<ReviewResponseDto> reviewResponseDtos = reviewDao.retriveContentReviewList(contentId);
		tourDetail.setReviewResponseDtos(reviewResponseDtos);
		
		StarResponseDto isFavorite = stardDao.retriveMyStar(userId, contentId);
		if (isFavorite == null) {
			tourDetail.setFavorite(false);
		} else {
			tourDetail.setFavorite(true);
		}
		return tourDetail;
	}

	@Override
	public List<CheckResponseDto> retrieveUserCheckList(int userId) {
		return reviewDao.retrieveUserCheckList(userId);
	}

	@Override
	public List<TourResponseDto> tourRecommendList() {
		return tourDao.tourRecommendList();
	}

	@Override
	public List<TourResponseDto> tourRelateList(int contentId) {
		TourDetailResponseDto tourDetail = tourDao.tourDetail(contentId);
		int sidoCode = tourDetail.getSidoCode();
		return tourDao.tourRelateList(sidoCode);
	}

	@Override
	public List<TourResponseDto> mainTourListByScore(int userId) {
		List<TourResponseDto> list = new ArrayList<>();
		if (userId == 0) {
			// 로그인 하지 않은 상태
			list = tourDao.mainTourListByScore();
			for (TourResponseDto tour : list) {
				tour.setFavorite(false);
			}
			return list;
		} 
		list = tourDao.mainTourListByScore();
		
		for (TourResponseDto tour : list) {
			if (stardDao.retriveMyStar(userId, tour.getContentId()) != null) {
				tour.setFavorite(true);
			} else {
				tour.setFavorite(false);
			}
		}
		return list;
	}

	@Override
	public TourResultDto tourRegionList(TourParamDto tourParamDto) {
		TourResultDto tourResultDto = new TourResultDto();
		List<TourResponseDto> list = new ArrayList<>();
		int count = 0;
		if (tourParamDto.getCategory().isEmpty() && tourParamDto.getOption().isEmpty()) {
			// 기본 list 조회
			list = tourDao.tourRegionList(tourParamDto);
			count = tourDao.tourRegionListCount(tourParamDto.getRegion());
		} else if (!tourParamDto.getCategory().isEmpty() && !tourParamDto.getOption().isEmpty()) {
			// 카테고리 + 정렬 둘 다 할때
			System.out.println("카테고리 + 정렬 o");
			list = tourDao.tourRegionByCategoryOrderByOptionList(tourParamDto);
			count = tourDao.tourListByRegionCategoryCount(tourParamDto);
		} else if(tourParamDto.getCategory().isEmpty() && !tourParamDto.getOption().isEmpty()) {
			// 정렬 + 카테고리 x
			list = tourDao.tourRegionOrderByOptionList(tourParamDto);
			count = tourDao.tourRegionListCount(tourParamDto.getRegion());
		} else { 
			// 정렬 x + 카테고리 o
			list = tourDao.tourRegionByCategoryList(tourParamDto);
			count = tourDao.tourListByRegionCategoryCount(tourParamDto);
		}
		
		
		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);
		}
		
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		
		return tourResultDto;
	}

	@Override
	public TourResultDto tourListByCategory(TourParamDto tourParamDto) {
		TourResultDto tourResultDto = new TourResultDto();
		List<TourResponseDto> list = new ArrayList<>();
		int count = tourDao.tourListByCategoryCount(tourParamDto);
		
		if (tourParamDto.getOption().isEmpty()) {
			System.out.println("without Option");
			list = tourDao.tourListByCategory(tourParamDto);
		} else {
			System.out.println(tourParamDto.getOption());
			list = tourDao.tourListByCategoryOrderByOption(tourParamDto);
			
		}
		
		System.out.println(list);
		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);
		}
		
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		return tourResultDto;
	}

	@Override
	public TourResultDto tourSidoList() {
		List<TourSidoResponseDto> sidoList = tourDao.tourSidoList();
		TourResultDto tourResultDto = new TourResultDto();
		
		tourResultDto.setSidoList(sidoList);
		return tourResultDto;
		
	}

	@Override
	public TourResultDto tourGugunList(int sidoCode) {
		List<TourGugunResponseDto> list = tourDao.tourGugunList(sidoCode);
		TourResultDto tourResultDto = new TourResultDto();
		
		tourResultDto.setGugunList(list);
		return tourResultDto;
	}

	@Override
	public TourResultDto searchTourbyWordAndSido(TourParamDto tourParamDto) {
		List<TourResponseDto> list = tourDao.searchTourbyWordAndSido(tourParamDto);
		int count = tourDao.searchTourByWordAndSidoCount(tourParamDto);
		
		TourResultDto tourResultDto = new TourResultDto();
		
		tourResultDto.setCount(count);
		tourResultDto.setList(list);
		
		return tourResultDto;
	}

	@Override
	public TourResultDto searchTourByWordAndSidoByCategory(TourParamDto tourParamDto) {
		TourResultDto tourResultDto = new TourResultDto();
		
		List<TourResponseDto> list = tourDao.searchTourByWordAndSidoByCategory(tourParamDto);
		int count = tourDao.searchTourByWordAndSidoByCategoryCount(tourParamDto);
		
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		return tourResultDto;
	}
	

}
