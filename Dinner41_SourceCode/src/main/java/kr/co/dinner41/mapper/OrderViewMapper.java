package kr.co.dinner41.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.dinner41.vo.OrderViewVO;

public class OrderViewMapper implements RowMapper<OrderViewVO>{

	@Override
	public OrderViewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderViewVO orderView = new OrderViewVO();
		orderView.setOrderId(rs.getInt("order_id"));
		orderView.setStoreId(rs.getInt("store_id"));
		orderView.setUserId(rs.getInt("user_id"));
		orderView.setOrder_order_date(rs.getTimestamp("order_order_date"));
		orderView.setOrder_reserve_date(rs.getTimestamp("order_reserve_date"));
		orderView.setOrder_pickup_date(rs.getTimestamp("order_pickup_date"));
		orderView.setPrice(rs.getInt("price"));
		orderView.setStoreName(rs.getString("store_name"));
		orderView.setUserName(rs.getString("user_name"));
		return orderView;
	}
}
