package kr.co.dinner41.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.exception.menu2order.Menu2OrderException;
import kr.co.dinner41.mapper.Menu2OrderViewMapper;
import kr.co.dinner41.vo.Menu2OrderVO;
import kr.co.dinner41.vo.Menu2OrderViewVO;

@Repository("menu2orderDao")
public class Menu2OrderDaoImpl implements Menu2OrderDao {

	@Autowired
	private JdbcTemplate jTemp;

	@Override
	public void insert(Menu2OrderVO menu2order) throws Menu2OrderException {
		String sql = "INSERT INTO menu_to_orders VALUES(?,?,?,?)";
		jTemp.update(sql, 
				menu2order.getOrder().getId(),
				menu2order.getStore().getId(),
				menu2order.getMenu().getId(),
				menu2order.getAmount());
	}

	@Override
	public void delete(Menu2OrderVO menu2order) throws Menu2OrderException {
		// 향후 구현
	}
	
	@Override 
	public List<Menu2OrderViewVO> selectAll() {
		List<Menu2OrderViewVO> list = null;
		String sql = "SELECT \r\n" + 
				"B.order_id order_id, \r\n" + 
				"B.store_name store_name, \r\n" + 
				"A.menu_name menu_name, \r\n" + 
				"B.menu_to_order_amount amount\r\n" + 
				"FROM menus A,\r\n" + 
				"(SELECT \r\n" + 
				"	A.order_id order_id, \r\n" + 
				"	A.store_id store_id, \r\n" + 
				"	A.menu_id menu_id, \r\n" + 
				"	A.menu_to_order_amount menu_to_order_amount, \r\n" + 
				"	B.store_name\r\n" + 
				"FROM menu_to_orders A, stores B\r\n" + 
				"WHERE A.store_id = B.store_id) AS B\r\n" + 
				"WHERE A.menu_id = B.menu_id AND A.store_id = B.store_id\r\n" + 
				"ORDER BY order_id";

		list = jTemp.query(sql, new Menu2OrderViewMapper());
		return list;
	}

	@Override
	public List<Menu2OrderViewVO> selectAllMenu2OrderView(int orderId) {
		List<Menu2OrderViewVO> list = null;
		String sql = "SELECT \r\n" + 
				"B.order_id order_id, \r\n" + 
				"B.store_name store_name, \r\n" + 
				"A.menu_name menu_name, \r\n" + 
				"B.menu_to_order_amount amount\r\n" + 
				"FROM menus A,\r\n" + 
				"(SELECT \r\n" + 
				"	A.order_id order_id, \r\n" + 
				"	A.store_id store_id, \r\n" + 
				"	A.menu_id menu_id, \r\n" + 
				"	A.menu_to_order_amount menu_to_order_amount, \r\n" + 
				"	B.store_name\r\n" + 
				"FROM menu_to_orders A, stores B\r\n" + 
				"WHERE A.store_id = B.store_id) AS B\r\n" + 
				"WHERE A.menu_id = B.menu_id AND A.store_id = B.store_id AND B.order_id = ?\r\n" + 
				"ORDER BY order_id";
		list = jTemp.query(sql, new Menu2OrderViewMapper(), orderId);
		return list;
	}
}
