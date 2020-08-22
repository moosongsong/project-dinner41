package kr.co.dinner41.service.menu;

import kr.co.dinner41.exception.menu.MenuException;

public interface MenuDeleteService {

	public void execute(int storeId, int menuId) throws MenuException;
	
}
