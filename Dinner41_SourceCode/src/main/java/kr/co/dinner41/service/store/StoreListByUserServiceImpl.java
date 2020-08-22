package kr.co.dinner41.service.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.dinner41.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.ReviewDao;
import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.StoreListByUserViewVO;

@Service("storeListByUserService")
public class StoreListByUserServiceImpl implements StoreListByUserService {
	 public static final int PAGE_SIZE = 5;
	 public static final int NUMBER_OF_PAGE_IN_ONE_PAGE = 4;

	@Autowired
	@Qualifier("storeDao")
	StoreDao storeDao;
	
	@Autowired
	@Qualifier("reviewDao")
	ReviewDao reviewDao;

	@Override
	public List<StoreListByUserViewVO> execute(String category, String keyword, double userLatitude, double userLongitude, int page) throws StoreException, ReviewException {
		List<StoreListByUserViewVO> storeListByUserViews = null;
		HashMap<StoreListByUserViewVO,Double> map = null;
		List<Double> averageScores =null;
		
		
			if(category.equals("all-")) {
				if(keyword.equals("all-")) {
					//storeListByUserViews = storeDao.selectViewByStoreNameOrMenuName("%", userLatitude, userLongitude, page, PAGESIZE);
					storeListByUserViews = storeDao.selectViewByCategoryName("%",userLatitude,userLongitude,page, PAGE_SIZE);
				}
				else {
					storeListByUserViews = storeDao.selectViewByStoreNameOrMenuName(keyword, userLatitude, userLongitude, page, PAGE_SIZE);
				}
				
			}
			else {
				if(keyword.equals("all-")) {
					storeListByUserViews = storeDao.selectViewByCategoryName(category,userLatitude,userLongitude,page, PAGE_SIZE);
				}
				else {
					//카테고리와 키워드를 둘다 이용하여 검색
				}
				
			}
		
		return storeListByUserViews;
	}

	@Override
	public List<PageVO> getPages(String category, String keyword, double userLatitude, double userLongitude, int page) throws StoreException {
		List<PageVO> list = new ArrayList<>();
		int totalRecord = 0;

		if (category.equals("all-")) {
			if (keyword.equals("all-")) {
				totalRecord = storeDao.getTotalCountByCategoryName("%", userLatitude, userLongitude, page, PAGE_SIZE);
			} else {
				totalRecord = storeDao.getTotalCountByStoreNameOrMenuName(keyword, userLatitude, userLongitude, page, PAGE_SIZE);
			}

		} else {
			if (keyword.equals("all-")) {
				totalRecord = storeDao.getTotalCountByCategoryName(category, userLatitude, userLongitude, page, PAGE_SIZE);
			} else {
				//카테고리와 키워드를 둘다 이용하여 검색
			}

		}

		int totalPage = totalRecord / PAGE_SIZE;
		if ((totalRecord % PAGE_SIZE) != 0) {
			totalPage = totalPage + 1;
		}

		if (page > totalPage) {
			page = totalPage;
		}
		if (page < 1) {
			page = 1;
		}

		int startPoint = page / NUMBER_OF_PAGE_IN_ONE_PAGE;
		int startPage = startPoint * NUMBER_OF_PAGE_IN_ONE_PAGE + 1;

		int endPage = startPage + NUMBER_OF_PAGE_IN_ONE_PAGE - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		int first = 0;
		if (startPage > 1) {
			first = startPage - 1;
		}
		if (startPage <= 1) {
			first = 1;
		}
		PageVO firstPage = new PageVO("<<", first);

		int last = 0;
		if (endPage < totalPage) {
			last = endPage + 1;
		}
		if (endPage >= totalPage) {
			last = endPage;
		}
		PageVO lastPage = new PageVO(">>", last);

		list.add(firstPage);
		for (int i = startPage; i <= endPage; i++) {
			PageVO pageVO = new PageVO("" + i, i);
			list.add(pageVO);
		}
		list.add(lastPage);

		return list;
	}
}
