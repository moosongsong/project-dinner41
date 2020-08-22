package kr.co.dinner41.service.cart;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.MenuDao;
import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.vo.CartVO;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.StoreVO;

@Service("cartInsert")
public class CartInsertServiceImpl implements CartInsertService {

	@Autowired
	@Qualifier("storeDao")
	StoreDao sDao;
	
	@Autowired
	@Qualifier("menuDao")
	MenuDao mDao;
	
	@Override
	public void execute(HttpSession session, int storeId, int menuId) {
		
		@SuppressWarnings("unchecked")
		List<CartVO> carts = (List<CartVO>)session.getAttribute("carts");
		
		StoreVO store = sDao.selectById(storeId);
		MenuVO menu = null;
		try {
			menu = mDao.selectByMenuIdStoreId(menuId, storeId);
		}
		catch (MenuException e) {
			e.printStackTrace();
		}
		
		System.out.println("메뉴가격 : " + menu.getPrice());
		
		carts.add(new CartVO(storeId, menuId, store.getName(), menu.getName(), 0, menu.getPrice()));
		session.removeAttribute("carts");
		session.setAttribute("carts", carts);
	}
}
