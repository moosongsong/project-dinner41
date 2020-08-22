package kr.co.dinner41.service.store;

import javax.servlet.http.HttpSession;

import kr.co.dinner41.exception.store.StoreException;

public interface StoreDeleteService {
	void execute(int storeId) throws StoreException;
}
