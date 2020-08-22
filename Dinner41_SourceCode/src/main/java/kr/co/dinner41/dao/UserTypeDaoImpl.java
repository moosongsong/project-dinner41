package kr.co.dinner41.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.exception.usertype.UserTypeDeleteFailedException;
import kr.co.dinner41.exception.usertype.UserTypeException;
import kr.co.dinner41.exception.usertype.UserTypeInsertFailedException;
import kr.co.dinner41.exception.usertype.UserTypeUpdateFailedException;
import kr.co.dinner41.mapper.UserTypeMapper;
import kr.co.dinner41.vo.UserTypeVO;

@Repository("userTypeDao")
public class UserTypeDaoImpl implements UserTypeDao {

	@Autowired
	private JdbcTemplate jTemp;

	@Override
	public void insert(UserTypeVO userType)throws UserTypeException {
		String sql="INSERT INTO user_types VALUES (?,?)";
		int result=0;
		try {
			result=jTemp.update(sql,userType.getId(),userType.getName());
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			throw new UserTypeInsertFailedException();
		}
		if(result==0) {
			throw new UserTypeInsertFailedException();
		}
	}

	@Override
	public void delete(String id) throws UserTypeException{
		String sql="DELETE FROM user_types WHERE user_type_id=?";
		int result=0;
		try {
		result=jTemp.update(sql,id);
		}catch(DataAccessException e) {
			e.printStackTrace();
			throw new UserTypeDeleteFailedException();
		}
		if(result==0) {
			throw new UserTypeDeleteFailedException();
		}
	}

	@Override
	public void update(String id, String newName) throws UserTypeException{
		String sql="UPDATE user_types SET user_type_name=? WHERE user_type_id=?";
		int result=0;
		try {
			result=jTemp.update(sql,newName,id);
		}catch(DataAccessException e) {
			e.printStackTrace();
			throw new UserTypeUpdateFailedException();
		}
		if(result==0) {
			throw new UserTypeUpdateFailedException();
		}
	}

	@Override
	public List<UserTypeVO> selectAll() throws UserTypeException{
		String sql="SELECT * FROM user_types"; 
		List<UserTypeVO> userTypes=jTemp.query(sql, new UserTypeMapper());
		return (userTypes.size()==0?null:userTypes);
	}

	
	public UserTypeVO selectById(String id)throws UserTypeException{
		String sql="SELECT * FROM user_types WHERE user_type_id=?"; 
		List<UserTypeVO> userTypes=jTemp.query(sql,new UserTypeMapper(),id);
		return (userTypes.size()==0?null:userTypes.get(0));
	}

	public UserTypeVO selectByName(String name)throws UserTypeException{
		String sql="SELECT * FROM user_types WHERE user_type_name=?"; 
		List<UserTypeVO> userTypes=jTemp.query(sql,new UserTypeMapper(),name);
		return (userTypes.size()==0?null:userTypes.get(0));
	}


}
