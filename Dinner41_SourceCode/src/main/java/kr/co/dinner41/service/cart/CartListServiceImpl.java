package kr.co.dinner41.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.MenuDao;
import kr.co.dinner41.vo.CartVO;

@Service("cartList")
public class CartListServiceImpl implements CartListService {

	@Override
	public List<CartVO> execute(List<CartVO> carts) {
		return carts;
	}
}
