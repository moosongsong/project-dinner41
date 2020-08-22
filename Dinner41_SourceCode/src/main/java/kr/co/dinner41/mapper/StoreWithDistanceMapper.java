package kr.co.dinner41.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import kr.co.dinner41.vo.OpenState;
import kr.co.dinner41.vo.StoreCategoryVO;
import kr.co.dinner41.vo.StoreStateVO;
import kr.co.dinner41.vo.StoreWithDistanceVO;
import kr.co.dinner41.vo.UserTypeVO;
import kr.co.dinner41.vo.UserVO;

public class StoreWithDistanceMapper implements RowMapper<StoreWithDistanceVO> {

	@Override
	public StoreWithDistanceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		int storeId= rs.getInt("store_id");
		int userId=rs.getInt("user_id");
		String storeCategoryId=rs.getString("store_category_id");
		int storeStateId=rs.getInt("store_state_id");
		String storeBusinessNumber=rs.getString("store_business_number");
		String storeName=rs.getString("store_name");
		String storeAddress=rs.getString("store_address");
		String storeSubAddress=rs.getString("store_sub_address");
		double storeLatitude=rs.getDouble("store_latitude");
		double storeLongitude=rs.getDouble("store_longitude");
		String storePhone=rs.getString("store_phone");
		String storeOperateTime=rs.getString("store_operate_time");
		String storePhoto=rs.getString("store_photo");
		String storeIntroduction= rs.getString("store_introduction");
		OpenState storeOpenState = OpenState.valueOf(rs.getString("store_open_state"));
		String storePayNumber= rs.getString("store_paynumber");
		double distance = rs.getDouble("distance");
		
//		double distance = 0;
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int columnCnt = rsmd.getColumnCount();
//		for(int i=1; i<=columnCnt; i++) {
//			if(rsmd.getColumnName(i).equals("distance")) {
//				distance = rs.getDouble("distance");
//			}
//		}
		
		
		
		String userTypeId = rs.getString("user_type_id");
		String userTypeName= rs.getString("user_type_name");
		UserTypeVO userType = new UserTypeVO(userTypeId,userTypeName);
		
		String userEmail=rs.getString("user_email");
		String userPassword=rs.getString("user_password");
		String userName=rs.getString("user_name");
		String userAddress=rs.getString("user_address");
		String userSubAddress=rs.getString("user_sub_address");
		double userLatitude=rs.getDouble("user_latitude");
		double userLongitude=rs.getDouble("user_longitude");
		String userPhone=rs.getString("user_phone");
		Timestamp userRegisterDate=rs.getTimestamp("user_register_date");
		Timestamp userRemoveDate=rs.getTimestamp("user_remove_date");
		UserVO user = new UserVO(userId,userType,userEmail,userPassword,userName,userAddress,userSubAddress,
								userLatitude,userLongitude,userPhone,userRegisterDate,userRemoveDate);
		
		String storeCategoryName = rs.getString("store_category_name");
		StoreCategoryVO storeCategory = new StoreCategoryVO(storeCategoryId,storeCategoryName);
		
		String storeStateName= rs.getString("store_state_name");
		StoreStateVO storeState = new StoreStateVO(storeStateId,storeStateName);
		
		StoreWithDistanceVO storeWithDistance = new StoreWithDistanceVO(storeId,user,storeCategory,storeState,storeBusinessNumber,storeName,storeAddress,storeSubAddress,
					storeLatitude,storeLongitude,storePhone,storeOperateTime,storePhoto,storeIntroduction,storeOpenState,storePayNumber,distance);
		return storeWithDistance;
	}
}

