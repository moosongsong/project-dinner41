package kr.co.dinner41.mapper;

import kr.co.dinner41.vo.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ReviewMapper implements RowMapper<ReviewVO> {
    @Override
    public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        int review_id = rs.getInt("review_id");

        int store_id= rs.getInt("store_id");

        int store_user_id=rs.getInt("store_user_id");
        String store_user_type_id = rs.getString("store_user_type_id");
        String store_user_type_name = rs.getString("store_user_type_name");
        UserTypeVO store_user_type = new UserTypeVO(store_user_type_id, store_user_type_name);
        String store_user_email = rs.getString("store_user_email");
        String store_user_password = rs.getString("store_user_password");
        String store_user_name = rs.getString("store_user_name");
        String store_user_address = rs.getString("store_user_address");
        String store_user_sub_address = rs.getString("store_user_sub_address");
        double store_user_latitude = rs.getDouble("store_user_latitude");
        double store_user_longitude = rs.getDouble("store_user_longitude");
        String store_user_phone = rs.getString("store_user_phone");
        Timestamp store_user_register_date = rs.getTimestamp("store_user_register_date");
        Timestamp store_user_remove_date = rs.getTimestamp("store_user_remove_date");
        UserVO store_user = new UserVO(store_user_id, store_user_type, store_user_email, store_user_password, store_user_name, store_user_address, store_user_sub_address, store_user_latitude, store_user_longitude, store_user_phone, store_user_register_date, store_user_remove_date);

        String store_category_id = rs.getString("store_category_id");
        String store_category_name = rs.getString("store_category_name");
        StoreCategoryVO store_category = new StoreCategoryVO(store_category_id, store_category_name);
        int store_state_id = rs.getInt("store_state_id");
        String store_state_name = rs.getString("store_state_name");
        StoreStateVO store_state = new StoreStateVO(store_state_id, store_state_name);
        String store_business_number = rs.getString("store_business_number");
        String store_name = rs.getString("store_name");
        String store_address = rs.getString("store_address");
        String store_sub_address = rs.getString("store_sub_address");
        double store_latitude = rs.getDouble("store_latitude");
        double store_longitude = rs.getDouble("store_longitude");
        String store_phone = rs.getString("store_phone");
        String store_operate_time = rs.getString("store_operate_time");
        String store_photo = rs.getString("store_photo");
        String store_introduction = rs.getString("store_introduction");
        OpenState store_open_state = OpenState.valueOf(rs.getString("store_open_state"));
        String store_paynumber = rs.getString("store_paynumber");
        StoreVO store = new StoreVO(store_id, store_user, store_category, store_state, store_business_number, store_name, store_address, store_sub_address, store_latitude, store_longitude, store_phone, store_operate_time, store_photo, store_introduction, store_open_state, store_paynumber);
        
        int user_id = rs.getInt("user_id");
        String user_type_id = rs.getString("user_type_id");
        String user_type_name = rs.getString("user_type_name");
        UserTypeVO user_type = new UserTypeVO(user_type_id, user_type_name);
        String user_email = rs.getString("user_email");
        String user_password = rs.getString("user_password");
        String user_name = rs.getString("user_name");
        String user_address = rs.getString("user_address");
        String user_subAddress = rs.getString("user_sub_address");
        double user_latitude = rs.getDouble("user_latitude");
        double user_longitude = rs.getDouble("user_longitude");
        String user_phone = rs.getString("user_phone");
        Timestamp user_registerDate = rs.getTimestamp("user_register_date");
        Timestamp user_removeDate = rs.getTimestamp("user_remove_date");
        UserVO user = new UserVO(user_id, user_type, user_email, user_password, user_name, user_address, user_subAddress, user_latitude, user_longitude, user_phone, user_registerDate, user_removeDate);

        String review_content = rs.getString("review_content");
        int review_score = rs.getInt("review_score");
        Timestamp review_date = rs.getTimestamp("review_date");
        int order_id = rs.getInt("review_order_id");

        return new ReviewVO(review_id, store, user, review_content, review_score, review_date, order_id);
    }
}
