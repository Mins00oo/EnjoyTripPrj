package com.mycom.joytrip.tour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

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
import com.mycom.joytrip.user.dto.UserDto;

@Service
public class TourServiceImpl implements TourService {

	@Autowired
	TourDao tourDao;

	@Autowired
	ReviewDao reviewDao;

	@Autowired
	StarDao stardDao;

	@Override
	public TourResultDto searchTourbyWord(TourParamDto tourParamDto, UserDto userDto) {
		TourResultDto tourResultDto = new TourResultDto();
		List<TourResponseDto> list = new ArrayList<>();
		if (tourParamDto.getCategory().isEmpty()) {
			list = tourDao.searchTourbyWord(tourParamDto);
			System.out.println(list);
		} else {
			list = tourDao.searchTourByWordAndCategory(tourParamDto);
		}
		
		if (!tourParamDto.getAgainSearchWord().isEmpty()) {
			System.out.println("여기가 그냥 검색어임");
			if (tourParamDto.getCategory().isEmpty()) {
				list = tourDao.searchAgainTourByWord(tourParamDto);
			} else {
				list = tourDao.searchAgainTourByWordAndCategory(tourParamDto);
			}

			// list는 전체 데이터가 되어야 함
			List<TourResponseDto> againSearchList = list.stream()
					.filter(TourResponseDto -> TourResponseDto.getAddr1().contains(tourParamDto.getAgainSearchWord())
							|| TourResponseDto.getTitle().contains(tourParamDto.getAgainSearchWord()))
					.collect(Collectors.toList());
			
			List<TourResponseDto> result = againSearchList.stream()
							.skip(tourParamDto.getOffset())
							.limit(9)
							.collect(Collectors.toList());
			tourResultDto.setList(result);
			tourResultDto.setCount(againSearchList.size());

			for (TourResponseDto tourResponseDto : againSearchList) {
				int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
				tourResponseDto.setReviewCount(reviewCount);
				if (userDto == null) {
					tourResponseDto.setFavorite(false);
				} else {
					if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
						tourResponseDto.setFavorite(false);
					} else {
						tourResponseDto.setFavorite(true);
					}
					
				}
			}
			return tourResultDto;
		}

		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);
			if (userDto == null) {
				tourResponseDto.setFavorite(false);
			} else {
				if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
					tourResponseDto.setFavorite(false);
				} else {
					tourResponseDto.setFavorite(true);
				}
				
			}
		}

		int count = tourDao.tourListSearchWordTotalCount(tourParamDto);
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		return tourResultDto;
	}

	@Override
	public List<TourResponseDto> orderTourList(String order) {
		return tourDao.orderTourList(order);
	}

	@Override
	public TourResultDto tourList(TourParamDto tourParamDto, UserDto userDto) {
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
			if (userDto == null) {
				tourResponseDto.setFavorite(false);
			} else {
				if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
					tourResponseDto.setFavorite(false);
				} else {
					tourResponseDto.setFavorite(true);
				}

			}
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
	public TourDetailResponseDto tourDetail(UserDto userDto, int contentId) {
		TourDetailResponseDto tourDetail = tourDao.tourDetail(contentId);

		List<ReviewResponseDto> reviewResponseDtos = reviewDao.retriveContentReviewList(contentId);
		tourDetail.setReviewResponseDtos(reviewResponseDtos);

		if (userDto == null) {
			tourDetail.setFavorite(false);
		} else {
			StarResponseDto isFavorite = stardDao.retriveMyStar(userDto.getUserId(), contentId);
			if (isFavorite == null) {
				tourDetail.setFavorite(false);
			} else {
				tourDetail.setFavorite(true);
			}
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
	public TourResultDto tourRelateList(int contentId, UserDto userDto) {
		TourResultDto tourResultDto = new TourResultDto();
		TourDetailResponseDto tourDetail = tourDao.tourDetail(contentId);
		int sidoCode = tourDetail.getSidoCode();
		List<TourResponseDto> list = tourDao.tourRelateList(sidoCode);

		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);

			if (userDto == null) {
				tourResponseDto.setFavorite(false);
			} else {
				if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
					tourResponseDto.setFavorite(false);
				} else {
					tourResponseDto.setFavorite(true);
				}
			}
		}

		tourResultDto.setList(list);

		return tourResultDto;
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
	public TourResultDto tourRegionList(TourParamDto tourParamDto, UserDto userDto) {
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
		} else if (tourParamDto.getCategory().isEmpty() && !tourParamDto.getOption().isEmpty()) {
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

			if (userDto == null) {
				tourResponseDto.setFavorite(false);
			} else {
				if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
					tourResponseDto.setFavorite(false);
				} else {
					tourResponseDto.setFavorite(true);
				}

			}
		}

		tourResultDto.setList(list);
		tourResultDto.setCount(count);

		return tourResultDto;
	}

	@Override
	public TourResultDto tourListByCategory(TourParamDto tourParamDto, UserDto userDto) {
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

		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);
			if (userDto == null) {
				tourResponseDto.setFavorite(false);
			} else {
				if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
					tourResponseDto.setFavorite(false);
				} else {
					tourResponseDto.setFavorite(true);
				}

			}
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
	public TourResultDto searchTourbyWordAndSido(TourParamDto tourParamDto, UserDto userDto) {
		TourResultDto tourResultDto = new TourResultDto();
		List<TourResponseDto> list = new ArrayList<>();
		list = tourDao.searchTourbyWordAndSido(tourParamDto);

		if (!tourParamDto.getAgainSearchWord().isEmpty()) {
			list = tourDao.searchAgainTourbyWordAndSido(tourParamDto);

			List<TourResponseDto> againSearchList = list.stream()
					.filter(tourResponseDto -> tourResponseDto.getAddr1().contains(tourParamDto.getAgainSearchWord())
							|| tourResponseDto.getTitle().contains(tourParamDto.getAgainSearchWord()))
					.collect(Collectors.toList());
			int count = againSearchList.size();
			List<TourResponseDto> result = againSearchList.stream()
							.skip(tourParamDto.getOffset())
							.limit(9)
							.collect(Collectors.toList());
			tourResultDto.setList(result);
			tourResultDto.setCount(count);

			for (TourResponseDto tourResponseDto : againSearchList) {
				int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
				tourResponseDto.setReviewCount(reviewCount);
				if (userDto == null) {
					tourResponseDto.setFavorite(false);
				} else {
					if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
						tourResponseDto.setFavorite(false);
					} else {
						tourResponseDto.setFavorite(true);
					}

				}
			}
			return tourResultDto;
		}

		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);
			if (userDto == null) {
				tourResponseDto.setFavorite(false);
			} else {
				if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
					tourResponseDto.setFavorite(false);
				} else {
					tourResponseDto.setFavorite(true);
				}

			}
		}

		int count = tourDao.searchTourByWordAndSidoCount(tourParamDto);
		tourResultDto.setCount(count);
		tourResultDto.setList(list);

		return tourResultDto;
	}

	@Override
	public TourResultDto searchTourByWordAndSidoByCategory(TourParamDto tourParamDto, UserDto userDto) {
		TourResultDto tourResultDto = new TourResultDto();

		List<TourResponseDto> list = tourDao.searchTourByWordAndSidoByCategory(tourParamDto);

		if (!tourParamDto.getAgainSearchWord().isEmpty()) {
			List<TourResponseDto> againSearchList = list.stream()
					.filter(TourResponseDto -> TourResponseDto.getAddr1().contains(tourParamDto.getAgainSearchWord())
							|| TourResponseDto.getTitle().contains(tourParamDto.getAgainSearchWord()))
					.collect(Collectors.toList());
			tourResultDto.setList(againSearchList);
			tourResultDto.setCount(againSearchList.size());

			for (TourResponseDto tourResponseDto : againSearchList) {
				int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
				tourResponseDto.setReviewCount(reviewCount);
				if (userDto == null) {
					tourResponseDto.setFavorite(false);
				} else {
					if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
						tourResponseDto.setFavorite(false);
					} else {
						tourResponseDto.setFavorite(true);
					}

				}
			}
			return tourResultDto;
		}

		for (TourResponseDto tourResponseDto : list) {
			int reviewCount = reviewDao.tourReviewCount(tourResponseDto.getContentId());
			tourResponseDto.setReviewCount(reviewCount);
			if (userDto == null) {
				tourResponseDto.setFavorite(false);
			} else {
				if (stardDao.retriveMyStar(userDto.getUserId(), tourResponseDto.getContentId()) == null) {
					tourResponseDto.setFavorite(false);
				} else {
					tourResponseDto.setFavorite(true);
				}

			}
		}

		int count = tourDao.searchTourByWordAndSidoByCategoryCount(tourParamDto);
		tourResultDto.setList(list);
		tourResultDto.setCount(count);
		return tourResultDto;
	}

}
