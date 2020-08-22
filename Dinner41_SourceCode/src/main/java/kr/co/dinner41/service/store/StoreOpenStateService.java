package kr.co.dinner41.service.store;

import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.OpenState;


public interface StoreOpenStateService {
	void execute(int storeId,OpenState openState) throws StoreException;
}
