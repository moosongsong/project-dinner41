package kr.co.dinner41.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.MenuDaoImpl;
import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.vo.MenuVO;

@Service("menuViewService")
public class MenuViewServiceImpl implements MenuViewService {

	@Autowired
	private MenuDaoImpl menuDao;
	
	@Override
	public MenuVO execute(int storeId, int menuId) {
		MenuVO menuVO = null;
		try {
			menuVO = menuDao.selectByMenuIdStoreId(menuId, storeId);
		}
		catch(MenuException e) {
			e.printStackTrace();
		}
		return menuVO;
	}

}
