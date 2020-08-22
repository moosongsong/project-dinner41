package kr.co.dinner41.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.MenuDaoImpl;
import kr.co.dinner41.exception.menu.MenuException;

@Service("menuUpdateAmountService")
public class MenuUpdateAmountServiceImpl implements MenuUpdateAmountService {

	
	@Autowired
	@Qualifier("menuDao")
	private MenuDaoImpl menuDao;
	
	
	
	@Override
	public void execute(int storeId, int menuId, int count) throws MenuException {
		menuDao.updateAmount(menuId, storeId,count);
		
	}

}
