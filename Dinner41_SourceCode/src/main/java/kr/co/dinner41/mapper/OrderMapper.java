package kr.co.dinner41.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.dinner41.vo.OrderVO;
import kr.co.dinner41.vo.UserVO;

public class OrderMapper implements RowMapper<OrderVO> {

	@Override
	public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderVO order = new OrderVO();
		UserVO user = new UserVO();
		order.setId(rs.getInt("order_id"));
		user.setId(rs.getInt("user_id"));
		order.setUser(user);
		order.setOrderDate(rs.getTimestamp("order_order_date"));
		order.setReserveDate(rs.getTimestamp("order_reserve_date"));
		order.setPickupDate(rs.getTimestamp("order_pickup_date"));
		order.setPrice(rs.getInt("price"));
		return order;
	}
}
