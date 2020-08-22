package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.exception.order.OrderException;
import kr.co.dinner41.vo.OrderVO;
import kr.co.dinner41.vo.OrderViewVO;

public interface OrderDao {
	void insert(OrderVO order) throws OrderException;
	void delete(int orderId) throws OrderException;
	void update(int orderId) throws OrderException;;
	OrderVO selectById(int orderId) throws OrderException;;
	List<OrderVO> selectAll() throws OrderException;;
	List<OrderViewVO> selectAllOrderByUser(int page, int pageSize, int userId, String type) throws OrderException;
	List<OrderViewVO> selectAllOrderByStore(int page, int pageSize,int storeId, String type) throws OrderException;
	int getTotalRecordForUser(int userId, String type);
	int getTotalRecordForStore(int storeId, String type);
}

