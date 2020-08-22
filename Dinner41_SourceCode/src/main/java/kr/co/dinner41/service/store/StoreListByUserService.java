package kr.co.dinner41.service.store;

import java.util.List;

import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.StoreListByUserViewVO;

public interface StoreListByUserService {
	public List<StoreListByUserViewVO> execute(String category, String keyword,double userLatitude, double userLongitude, int page) throws StoreException,ReviewException;

	List<PageVO> getPages(String category, String keyword, double userLatitude, double userLongitude, int page) throws StoreException;
}
