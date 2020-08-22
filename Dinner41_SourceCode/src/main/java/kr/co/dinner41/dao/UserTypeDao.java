package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.exception.usertype.UserTypeException;
import kr.co.dinner41.vo.UserTypeVO;

public interface UserTypeDao {
	void insert(UserTypeVO userType)throws UserTypeException;
	void delete(String id)throws UserTypeException;
	void update(String id,String newName)throws UserTypeException;
	List<UserTypeVO> selectAll()throws UserTypeException;
	UserTypeVO selectById(String id)throws UserTypeException;
	UserTypeVO selectByName(String name)throws UserTypeException;
}
