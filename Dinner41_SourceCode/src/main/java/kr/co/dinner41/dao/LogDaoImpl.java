package kr.co.dinner41.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.vo.LogVO;

@Repository("logDao")
public class LogDaoImpl implements LogDao {
	
	@Autowired
	private JdbcTemplate jTemp;

	@Override
	public void insert(LogVO log) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMessage(int id, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateManager(int id, int managerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectByManagerId(int managerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String selectByStoreIdAndStoreStateId(int storeId, int storeStateId) {
		// TODO Auto-generated method stub
		return null;
	}

}
