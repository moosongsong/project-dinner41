package kr.co.dinner41.service.menu;

import kr.co.dinner41.exception.menu.MenuException;

public interface MenuUpdateAmountService {

	
	public void execute(int storeId, int menuId, int count) throws MenuException;
}
