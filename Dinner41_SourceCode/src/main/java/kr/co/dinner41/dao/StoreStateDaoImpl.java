package kr.co.dinner41.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.exception.store.StoreStateDeleteFailedException;
import kr.co.dinner41.exception.store.StoreStateException;
import kr.co.dinner41.exception.store.StoreStateInsertFailedException;
import kr.co.dinner41.exception.store.StoreStateSelectFailedException;
import kr.co.dinner41.vo.StoreStateVO;

@Repository("storeStateDao")
public class StoreStateDaoImpl implements StoreStateDao {
	
	@Autowired
	private JdbcTemplate jTemp;
	
	@Override
	public void insert(StoreStateVO storeState) throws StoreStateException {
		String sql = "insert into store_states values (default,?)";
		int result=0;
		try {
			result=jTemp.update(sql,storeState.getName());
		}
		catch(Exception e) {
			throw new StoreStateInsertFailedException(e.getMessage());
		}
		if(result==0) {
			throw new StoreStateInsertFailedException("매장 상태 등록을 시도하였으나 영향받은 행이 없습니다.");
		}
	}

	@Override
	public void delete(int id) throws StoreStateException{
		String sql = "delete from store_states where store_state_id=?";
		int result = 0;
		try {
			result=jTemp.update(sql,id);
		}
		catch(Exception e) {
			throw new StoreStateDeleteFailedException(e.getMessage());
		}
		if(result==0) {
			throw new StoreStateDeleteFailedException("매장 상태 삭제를 시도하였으나 영향받은 행이 없습니다.");
		}
	}
	
	@Override
	public StoreStateVO selectById(int id) throws StoreStateException{
		String sql = "select * from store_states where store_state_id=?";
		List<StoreStateVO> storeStates=null;
		try {
		storeStates = jTemp.query(sql, new RowMapper<StoreStateVO>() {

			@Override
			public StoreStateVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id= rs.getInt("store_state_id");
				String name= rs.getString("store_state_name");
				StoreStateVO storeState = new StoreStateVO(id,name);
				return storeState;
			}
			
		},id);
		}
		catch(Exception e) {
			throw new StoreStateSelectFailedException(e.getMessage());
		}
		return (storeStates.size()>0? storeStates.get(0):null);
	}
	

	@Override
	public List<StoreStateVO> selectAll() throws StoreStateException{
		String sql = "select * from store_states";
		List<StoreStateVO> storeStates =null;
		try {
		 storeStates = jTemp.query(sql, new RowMapper<StoreStateVO>() {

			@Override
			public StoreStateVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id= rs.getInt("store_state_id");
				String name= rs.getString("store_state_name");
				StoreStateVO storeState = new StoreStateVO(id,name);
				return storeState;
			}
			
		});
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new StoreStateSelectFailedException(e.getMessage());
		}
		return (storeStates.size()>0? storeStates:null);
	}

}
