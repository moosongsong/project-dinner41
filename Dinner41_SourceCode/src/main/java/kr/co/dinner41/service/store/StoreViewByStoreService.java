package kr.co.dinner41.service.store;

import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.StoreVO;

public interface StoreViewByStoreService {
	StoreVO execute(int userId) throws StoreException;
}
