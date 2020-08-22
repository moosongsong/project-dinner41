package kr.co.dinner41.service.store;

import java.util.List;

import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.StoreVO;

public interface StoreListByManagerService {
	List<StoreVO> execute(String storeStateName,String StoreName,int page) throws StoreException;
	List<PageVO> getPages(String storeStateName,String storeName,int nowPage);
}
