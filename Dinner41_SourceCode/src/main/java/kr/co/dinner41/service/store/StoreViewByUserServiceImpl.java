package kr.co.dinner41.service.store;

import kr.co.dinner41.dao.MenuDao;
import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.StoreVO;

import java.util.List;

@Service("storeViewByUserService")
public class StoreViewByUserServiceImpl implements StoreViewByUserService {
	public static final int PAGE_SIZE = 5;
	public static final int NUMBER_OF_PAGE_IN_ONE_PAGE = 4;

	@Autowired
	@Qualifier("storeDao")
	StoreDao storeDao;

	@Override
	public StoreVO execute(int storeId) throws StoreException {
		StoreVO store = storeDao.selectById(storeId);
		return store;
	}

	@Override
	public List<MenuVO> getMenus(int storeId) throws StoreException{
		return storeDao.getMenus(storeId);
	}
}
