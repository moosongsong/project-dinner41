package kr.co.dinner41.service.menu;

import kr.co.dinner41.vo.MenuVO;

public interface MenuViewService {

	MenuVO execute(int storeId, int menuId);
}
