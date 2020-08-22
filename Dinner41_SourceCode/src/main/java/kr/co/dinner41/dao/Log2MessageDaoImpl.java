package kr.co.dinner41.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.vo.Log2MessageVO;

@Repository("log2messageDao")
public class Log2MessageDaoImpl implements Log2MessageDao {

	@Autowired
	private JdbcTemplate jTemp;
	
	@Override
	public void insert(Log2MessageVO log2message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int logId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int logId, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Log2MessageVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
