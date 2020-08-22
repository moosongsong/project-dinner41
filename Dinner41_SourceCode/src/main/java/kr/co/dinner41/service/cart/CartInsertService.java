package kr.co.dinner41.service.cart;

import javax.servlet.http.HttpSession;

public interface CartInsertService {
	public void execute(HttpSession session, int storeId, int menuId);
}
