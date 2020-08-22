package kr.co.dinner41.service.store;

import javax.servlet.http.HttpSession;

import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.StoreVO;

public interface StoreViewByManagerService {
	StoreVO execute(HttpSession session) throws StoreException;
}
