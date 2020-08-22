package kr.co.dinner41.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.dinner41.vo.Menu2OrderViewVO;

public class Menu2OrderViewMapper implements RowMapper<Menu2OrderViewVO>{

	@Override
	public Menu2OrderViewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		Menu2OrderViewVO m2ov = new Menu2OrderViewVO();
		m2ov.setOrderId(rs.getInt("order_id"));
		m2ov.setStoreName(rs.getString("store_name"));
		m2ov.setMenuName(rs.getString("menu_name"));
		m2ov.setAmount(rs.getInt("amount"));
		return m2ov;
	}
}
