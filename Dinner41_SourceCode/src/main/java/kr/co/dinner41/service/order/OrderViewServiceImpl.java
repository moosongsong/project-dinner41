package kr.co.dinner41.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.OrderDao;
import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.vo.OrderVO;
import kr.co.dinner41.vo.UserVO;

@Service("orderView")
public class OrderViewServiceImpl implements OrderViewService {

	@Autowired
	@Qualifier("orderDao")
	private OrderDao oDao;
	
	@Autowired
	@Qualifier("userDao")
	private UserDao uDao;
	
	@Override
	public OrderVO execute(int orderId) {
		OrderVO order = null;
		order = oDao.selectById(orderId);
		UserVO user = uDao.selectById(order.getUser().getId());
		order.getUser().setName(user.getName());
		return order;
	}
}
