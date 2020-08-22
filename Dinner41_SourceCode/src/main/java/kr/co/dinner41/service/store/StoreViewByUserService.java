package kr.co.dinner41.service.store;

import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.StoreVO;

import java.util.List;

public interface StoreViewByUserService {
	StoreVO execute(int storeId) throws StoreException;
	List<MenuVO> getMenus(int storeId) throws StoreException;
}
