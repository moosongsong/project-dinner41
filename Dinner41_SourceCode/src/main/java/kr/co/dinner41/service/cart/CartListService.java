package kr.co.dinner41.service.cart;

import java.util.List;

import kr.co.dinner41.vo.CartVO;

public interface CartListService {
	public List<CartVO> execute(List<CartVO> carts);
	
}
