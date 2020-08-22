package kr.co.dinner41.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.MenuDaoImpl;
import kr.co.dinner41.exception.menu.MenuException;

@Service("menuDeleteService")
public class MenuDeleteServiceImpl implements MenuDeleteService {

	@Autowired
	@Qualifier("menuDao")
	private MenuDaoImpl menuDao;
	
	
	@Override
	public void execute(int storeId, int menuId) throws MenuException {
		menuDao.delete(menuId, storeId);
	}

}
