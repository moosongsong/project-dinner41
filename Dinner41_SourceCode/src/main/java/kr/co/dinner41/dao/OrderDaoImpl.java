package kr.co.dinner41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.exception.order.OrderException;
import kr.co.dinner41.mapper.OrderMapper;
import kr.co.dinner41.mapper.OrderViewMapper;
import kr.co.dinner41.vo.OrderVO;
import kr.co.dinner41.vo.OrderViewVO;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private JdbcTemplate jTemp;

	@Override
	public void insert(OrderVO order) throws OrderException {
		KeyHolder holder = new GeneratedKeyHolder();
		String sql = "INSERT INTO orders VALUES(DEFAULT, ?, ?, ?, DEFAULT, ?)";
		String[] columns = { "id" };

		jTemp.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, columns);
				pstmt.setInt(1, order.getUser().getId());
				pstmt.setTimestamp(2, order.getOrderDate());
				pstmt.setTimestamp(3, order.getReserveDate());
				pstmt.setInt(4, order.getPrice());
				return pstmt;
			}
		}, holder);
		Number number = holder.getKey();
		int value = number.intValue();
		order.setId(value);
	}

	@Override
	public void delete(int orderId) {
		// 향후 구현
	}

	@Override
	public void update(int orderId) throws OrderException {
		String sql = "UPDATE orders SET order_pickup_date=CURRENT_TIMESTAMP WHERE order_id=?";
		jTemp.update(sql, orderId);
	}

	@Override
	public OrderVO selectById(int orderId) {
		List<OrderVO> list = null;
		String sql = "SELECT * FROM orders WHERE order_id=?";
		list = jTemp.query(sql, new OrderMapper(), orderId);
		return (list.size() == 0 ? null : list.get(0));
	}

	@Override
	public List<OrderVO> selectAll() {
		List<OrderVO> list = null;
		String sql = "SELECT * FROM orders";
		list = jTemp.query(sql, new OrderMapper());
		return list;
	}

	@Override
	public List<OrderViewVO> selectAllOrderByUser(int page, int pageSize, int userId, String type)
			throws OrderException {
		List<OrderViewVO> list = null;
		String sql = null;
		String NULL_CHECK = null;
		String ORDER_BY = null;
		int startPoint = (page-1)*pageSize;

		// 주문 대기중인 주문 목록을 불러오기 위해서
		if (type.equals("WAIT")) {
			NULL_CHECK = "IS NULL\r\n";
			ORDER_BY = " ORDER BY A.order_order_date DESC";
		}
		// 수령 완료된 주문 목록을 불러오기 위해서
		else if (type.equals("COMP")) {
			NULL_CHECK = "IS NOT NULL\r\n";
			ORDER_BY = " ORDER BY A.order_pickup_date DESC";
		}
		else {
			System.out.println("selectAllOrderByUser : type으로 WAIT, COMP 이외의 인자가 들어왔습니다.");
			return null;
		}

		sql = "SELECT DISTINCT\r\n" + "	A.order_id order_id, \r\n" + "	C.store_id store_id,\r\n"
				+ "	A.user_id user_id,\r\n" + "	A.order_order_date order_order_date,\r\n"
				+ "	A.order_reserve_date order_reserve_date, \r\n" + "	A.order_pickup_date order_pickup_date, \r\n"
				+ "	A.price price, \r\n" + "	C.store_name store_name,\r\n" + "	D.user_name user_name\r\n"
				+ "FROM orders AS A, menu_to_orders AS B, stores AS C, users AS D\r\n"
				+ "WHERE A.user_id=? AND A.order_id = B.order_id AND B.store_id = C.store_id\r\n"
				+ "AND A.user_id=D.user_id\r\n" + "AND A.order_pickup_date " + NULL_CHECK + ORDER_BY+" LIMIT "+startPoint+", "+pageSize;

		list = jTemp.query(sql, new OrderViewMapper(), userId);
		return list;
	}

	@Override
	public List<OrderViewVO> selectAllOrderByStore(int page, int pageSize, int storeId, String type)
			throws OrderException {
		List<OrderViewVO> list = null;
		String sql = null;
		String NULL_CHECK = null;
		String ORDER_BY = null;
		int startPoint = (page-1)*pageSize;

		// 주문 대기중인 주문 목록을 불러오기 위해서
		if (type.equals("WAIT")) {
			NULL_CHECK = "IS NULL\r\n";
			ORDER_BY = " ORDER BY A.order_order_date DESC";
		}
		// 수령 완료된 주문 목록을 불러오기 위해서
		else if (type.equals("COMP")) {
			NULL_CHECK = "IS NOT NULL\r\n";
			ORDER_BY = " ORDER BY A.order_pickup_date DESC";
		}
		else {
			System.out.println("selectAllOrderByStore : type으로 WAIT, COMP 이외의 인자가 들어왔습니다.");
			return null;
		}

		sql = "SELECT DISTINCT\r\n" + "	A.order_id order_id, \r\n" + "	C.store_id store_id,\r\n"
				+ "	A.user_id user_id,\r\n" + "	A.order_order_date order_order_date,\r\n"
				+ "	A.order_reserve_date order_reserve_date, \r\n" + "	A.order_pickup_date order_pickup_date, \r\n"
				+ "	A.price price, \r\n" + "	C.store_name store_name,\r\n" + "	D.user_name user_name\r\n"
				+ "FROM orders AS A, menu_to_orders AS B, stores AS C, users AS D\r\n"
				+ "WHERE C.store_id=? AND A.order_id = B.order_id AND B.store_id = C.store_id\r\n"
				+ "AND A.user_id=D.user_id\r\n" + "AND A.order_pickup_date " + NULL_CHECK + ORDER_BY+" LIMIT "+startPoint+", "+pageSize;

		list = jTemp.query(sql, new OrderViewMapper(), storeId);
		return list;
	}

	@Override
	// 일반회원에 관한 주문목록의 개수를 가져옴
	public int getTotalRecordForUser(int userId, String type) {
		List<Integer> list = null;
		String sql = null;
		String NULL_CHECK = null;
		String ORDER_BY = null;

		// 주문 대기중인 주문 목록을 불러오기 위해서
		if (type.equals("WAIT")) {
			NULL_CHECK = "IS NULL\r\n";
			ORDER_BY = " ORDER BY A.order_order_date DESC";
		}
		// 수령 완료된 주문 목록을 불러오기 위해서
		else if (type.equals("COMP")) {
			NULL_CHECK = "IS NOT NULL\r\n";
			ORDER_BY = " ORDER BY A.order_pickup_date DESC";
		}
		else {
			System.out.println("getTotalRecordForUser : type으로 WAIT, COMP 이외의 인자가 들어왔습니다.");
			return 0;
		}

		sql = "SELECT COUNT(*) AS num \r\n" + "FROM ( \r\n" + "SELECT DISTINCT\r\n"
				+ "					A.order_id order_id,  \r\n" + "					C.store_id store_id, \r\n"
				+ "					A.user_id user_id,\r\n"
				+ "					A.order_order_date order_order_date,\r\n"
				+ "					A.order_reserve_date order_reserve_date, \r\n"
				+ "					A.order_pickup_date order_pickup_date,\r\n"
				+ "					A.price price,  \r\n" + "					C.store_name store_name, \r\n"
				+ "					D.user_name user_name \r\n"
				+ "				FROM orders AS A, menu_to_orders AS B, stores AS C, users AS D \r\n"
				+ "				WHERE A.user_id=? \r\n" + "					AND A.order_id = B.order_id \r\n"
				+ "					AND B.store_id = C.store_id \r\n" + "					AND A.user_id=D.user_id\r\n"
				+ "					AND A.order_pickup_date IS NULL\r\n"
				+ "				ORDER BY A.order_order_date DESC) AS sub;";

		list = jTemp.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("num");
			}
		}, userId);
		return (list.size() == 0 ? 0 : list.get(0));
	}

	@Override
	// 점주회원에 관한 주문목록의 개수를 가져옴
	public int getTotalRecordForStore(int storeId, String type) {
		List<Integer> list = null;
		String sql = null;
		String NULL_CHECK = null;
		String ORDER_BY = null;

		// 주문 대기중인 주문 목록을 불러오기 위해서
		if (type.equals("WAIT")) {
			NULL_CHECK = "IS NULL\r\n";
			ORDER_BY = " ORDER BY A.order_order_date DESC";
		}
		// 수령 완료된 주문 목록을 불러오기 위해서
		else if (type.equals("COMP")) {
			NULL_CHECK = "IS NOT NULL\r\n";
			ORDER_BY = " ORDER BY A.order_pickup_date DESC";
		}
		else {
			System.out.println("getTotalRecordForStore : type으로 WAIT, COMP 이외의 인자가 들어왔습니다.");
			return 0;
		}

		sql = "SELECT COUNT(*) AS num\r\n" + "FROM ( \r\n" + "SELECT DISTINCT\r\n"
				+ "					A.order_id order_id,  \r\n" + "					C.store_id store_id, \r\n"
				+ "					A.user_id user_id,\r\n"
				+ "					A.order_order_date order_order_date,\r\n"
				+ "					A.order_reserve_date order_reserve_date, \r\n"
				+ "					A.order_pickup_date order_pickup_date,\r\n"
				+ "					A.price price,  \r\n" + "					C.store_name store_name, \r\n"
				+ "					D.user_name user_name \r\n"
				+ "				FROM orders AS A, menu_to_orders AS B, stores AS C, users AS D \r\n"
				+ "				WHERE C.store_id=?\r\n" + "					AND A.order_id = B.order_id \r\n"
				+ "					AND B.store_id = C.store_id \r\n" + "					AND A.user_id=D.user_id\r\n"
				+ "					AND A.order_pickup_date IS NULL\r\n"
				+ "				ORDER BY A.order_order_date DESC) AS sub;";

		list = jTemp.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("num");
			}

		}, storeId);
		return (list.size() == 0 ? 0 : list.get(0));
	}

}
