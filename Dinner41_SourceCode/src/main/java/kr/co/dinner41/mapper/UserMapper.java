package kr.co.dinner41.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import kr.co.dinner41.vo.UserTypeVO;
import kr.co.dinner41.vo.UserVO;

public class UserMapper implements RowMapper<UserVO>{
	@Override
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id=rs.getInt("user_id");
		String typeId=rs.getString("user_type_id");
		String typeName=rs.getString("user_type_name");
		String email=rs.getString("user_email");
		String password=rs.getString("user_password");
		String name=rs.getString("user_name");
		String address=rs.getString("user_address");
		String subAddresss=rs.getString("user_sub_address");
		double latitude=rs.getDouble("user_latitude");
		double longitude=rs.getDouble("user_longitude");
		String phone=rs.getString("user_phone");
		Timestamp registerDate=rs.getTimestamp("user_register_date");
		Timestamp removeDate=rs.getTimestamp("user_remove_date");
		UserTypeVO userType=new UserTypeVO(typeId,typeName);
		UserVO user=new UserVO(id,userType,email,password,name,address,subAddresss,latitude,longitude,phone,registerDate,removeDate);
		return user;
	}
}
