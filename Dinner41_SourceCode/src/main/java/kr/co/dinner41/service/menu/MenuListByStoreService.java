package kr.co.dinner41.service.menu;

import java.util.List;

import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

public interface MenuListByStoreService {

	List<MenuVO> execute(int storeId, int page) throws MenuException;
	List<PageVO> getPages(int nowPage, int storeId);

}
