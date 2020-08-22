package kr.co.dinner41.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.OrderDao;

@Service("orderReceiptComplete")
public class OrderReceiptCompleteServiceImpl implements OrderReceiptCompleteService {

	@Autowired
	@Qualifier("orderDao")
	private OrderDao oDao;
	
	@Override
	public void execute(int orderId) {
		oDao.update(orderId);
	}
}
