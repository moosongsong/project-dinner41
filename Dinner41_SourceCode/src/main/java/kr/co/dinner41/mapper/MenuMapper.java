package kr.co.dinner41.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.OfferTypeVO;
import kr.co.dinner41.vo.OpenState;
import kr.co.dinner41.vo.StoreCategoryVO;
import kr.co.dinner41.vo.StoreStateVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserTypeVO;
import kr.co.dinner41.vo.UserVO;

public class MenuMapper implements RowMapper<MenuVO>{

	@Override
	public MenuVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String offerTypeName = rs.getString("offer_type_name");
		String offerTypeId = rs.getString("offer_type_id");
		OfferTypeVO type = new OfferTypeVO(offerTypeId, offerTypeName);
	
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
		
		
		int storeId= rs.getInt("store_id");
		int menuId = rs.getInt("menu_id");
		String menuTag = rs.getString("menu_tag");
		String menuName = rs.getString("menu_name");
		int menuPrice = rs.getInt("menu_price");
		int menuAmount = rs.getInt("menu_amount");
		String menuDescription = rs.getString("menu_description");
		String menuNotice = rs.getString("menu_notice");
		String menuPhoto = rs.getString("menu_photo");
		Timestamp menuRemoveDate = rs.getTimestamp("menu_remove_date");
		StoreVO store = new StoreVO(storeId,user,storeCategory, storeState, storeBusinessNumber, storeName, storeAddress, storeSubAddress,
				storeLatitude, storeLongitude, storePhone, storeOperateTime, storePhoto, storeIntroduction, storeOpenState,storePayNumber);
		
		//MenuVO menu = new MenuVO(storeId, menuId,offerTypeId, menuTag, menuName, menuPrice, menuAmount, menuDescription, menuNotice, menuPhoto,menuRemoveDate);
		
		 return new MenuVO(store, menuId, type, menuTag, menuName,menuPrice, menuAmount, menuDescription, menuNotice, menuPhoto, menuRemoveDate);
	
		
	}

	
}
