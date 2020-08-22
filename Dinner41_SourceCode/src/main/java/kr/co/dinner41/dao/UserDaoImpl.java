package kr.co.dinner41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.exception.user.NoSuchUserException;
import kr.co.dinner41.exception.user.UserDeleteFailedException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.exception.user.UserInsertFailedException;
import kr.co.dinner41.exception.user.UserSelectFailedException;
import kr.co.dinner41.exception.user.UserUpdateFailedException;
import kr.co.dinner41.mapper.UserMapper;
import kr.co.dinner41.vo.UserTypeVO;
import kr.co.dinner41.vo.UserVO;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jTemp;

	@Override
	public UserVO insert(UserVO user) throws UserException{
		final String sql="INSERT INTO users VALUES(default,?,?,PASSWORD(?),?,?,?,?,?,?,default,default)";
		final String[] columnNames= {"user_id"};
		int result=0;

		UserTypeVO type=user.getType();
		String email=user.getEmail();
		String password=user.getPassword();
		String name=user.getName();
		String address=user.getAddress();
		String subAddress=user.getSubAddress();
		double latitude=user.getLatitude();
		double longitude=user.getLongitude();
		String phone=user.getPhone();
		
		KeyHolder holder=new GeneratedKeyHolder();
		
		try {
			result=jTemp.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstmt=con.prepareStatement(sql, columnNames);
					pstmt.setString(1, type.getId());
					pstmt.setString(2, email);
					pstmt.setString(3, password);
					pstmt.setString(4, name);
					pstmt.setString(5, address);
					pstmt.setString(6,subAddress);
					pstmt.setDouble(7, latitude);
					pstmt.setDouble(8, longitude);
					pstmt.setString(9, phone);
					return pstmt;
				}
			},holder);
		}
		catch(DataAccessException e) {
			throw new UserInsertFailedException(e.getMessage());
		}
		if(result<=0) {
			throw new UserInsertFailedException("DB문제로 인해 회원 등록을 할 수 없습니다.");
		}
		
		Number key=holder.getKey();
		int insertedId=key.intValue();
		
		user.setId(insertedId);
		UserVO insertedUser=selectById(insertedId);
		
		return insertedUser;
	}

	@Override
	public void delete(int userId) throws UserException {
		String sql="UPDATE users SET user_remove_date=? WHERE user_id=?";
		int result=0;

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar calendar = Calendar.getInstance();
	    String now = sdf.format(calendar.getTime());

		try {
			result=jTemp.update(sql,now,userId);
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			throw new UserDeleteFailedException();
		}
		if(result==0) {
			throw new UserDeleteFailedException();
		}
	}

	@Override
	public UserVO update(UserVO user) throws UserException {
		if(user==null) {
			throw new UserUpdateFailedException("(UserDaoImpl)update:전달인자가 null이다");
		}
		String sql="UPDATE users SET user_password=PASSWORD(?), user_name=? , user_address=? ,"
				+ " user_sub_address=? , user_latitude=? , user_longitude=? , user_phone=? "
				+ "WHERE user_id=? ";
		String userPassword=user.getPassword();
		int userId=user.getId();
		String userName=user.getName();
		String userAddress=user.getAddress();
		String userSubAddress=user.getSubAddress();
		double userLatitude=user.getLatitude();
		double userLongitude=user.getLongitude();
		String userPhone=user.getPhone();
		int result=0;
		try {
			result=jTemp.update(sql,userPassword,userName,userAddress,userSubAddress,userLatitude,userLongitude,userPhone,userId);
		}catch(DataAccessException e) {
			throw new UserUpdateFailedException(e);
		}
		if(result==0) {
			throw new UserUpdateFailedException();
		}
		try {
			UserVO updatedUser=selectById(userId);
			if(updatedUser!=null) {
				return updatedUser;
			}
		}catch(UserException e) {
			throw new UserUpdateFailedException(e);
		}
		return null;
	}
	
	@Override
	public UserVO selectById(int id) throws UserException {
		String sql="SELECT * FROM user_view WHERE user_id=?";
		List<UserVO> users=null;
		try {
			users=jTemp.query(sql, new UserMapper(),id);
		}catch(DataAccessException e) {
			e.printStackTrace();
			throw new NoSuchUserException();
		}
		return (users.size()==0?null:users.get(0));
	}

	@Override
	public UserVO selectByEmail(String email) throws UserException {
		String sql="SELECT * FROM user_view WHERE user_email=?";
		List<UserVO> users=null;
		try {
			users=jTemp.query(sql, new UserMapper(),email);
		}catch(DataAccessException e) {
			e.printStackTrace();
			throw new UserSelectFailedException();
		}
		return (users.size()==0?null:users.get(0));
	}

	@Override
	public UserVO selectByEmailAndPassword(String email, String password) throws UserException {
		String sql="SELECT * FROM user_view WHERE user_email=? AND user_password=PASSWORD(?);";
		List<UserVO> users=null;
		try {
			users=jTemp.query(sql,new UserMapper(),email,password); 
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			throw new UserSelectFailedException();
		}
		return (users.size()==0?null:users.get(0));
	}

	@Override
	public List<UserVO> selectAll() throws UserException {
		String sql="SELECT * FROM user_view ";
		List<UserVO> users=null;
		try {
			users=jTemp.query(sql, new UserMapper());
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			throw new UserSelectFailedException();
		}
		return (users.size()==0?null:users);
	}

}
