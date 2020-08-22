package kr.co.dinner41.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.exception.store.StoreCategoryDeleteFailedException;
import kr.co.dinner41.exception.store.StoreCategoryException;
import kr.co.dinner41.exception.store.StoreCategoryInsertFailedException;
import kr.co.dinner41.exception.store.StoreCategorySelectFailedException;
import kr.co.dinner41.vo.StoreCategoryVO;

@Repository("storeCategoryDao")
public class StoreCategoryDaoImpl implements StoreCategoryDao {

	@Autowired
	private JdbcTemplate jTemp;
	
	@Override
	public void insert(StoreCategoryVO storeCategory) throws StoreCategoryException {
		String sql = "insert into store_categories values (?, ?)";
		int result=0;
		try {
			result= jTemp.update(sql,storeCategory.getId(),storeCategory.getName());
		}
		catch(Exception e) {
			throw new StoreCategoryInsertFailedException(e.getMessage());
		}
		if(result==0) {
			throw new StoreCategoryInsertFailedException("매장 카테고리 등록을 시도하였으나 영향받은 행이 없습니다.");
		}
	}

	@Override
	public void delete(String id) throws StoreCategoryException{
		String sql = "delete from store_categories where store_category_id=?";
		int result=0;
		try {
			result= jTemp.update(sql,id);
		}
		catch(Exception e) {
			throw new StoreCategoryDeleteFailedException(e.getMessage());
		}
		if(result==0) {
			throw new StoreCategoryDeleteFailedException("매장 카테고리 삭제를 시도하였으나 영향받은 행이 없습니다.");
		}
	}
	
	@Override
	public String selectIdByName(String name) throws StoreCategoryException {
		String sql= "select store_category_id from store_categories where store_category_name=?";
		List<String> storeCategoryId = null;
		try {
			storeCategoryId= jTemp.query(sql,new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					String id = rs.getString("store_category_id");
					return id;
				}
				
			},name);
		}
		catch(Exception e) {
			throw new StoreCategorySelectFailedException(e.getMessage());
		}
		return (storeCategoryId.size()>0 ? storeCategoryId.get(0): null);
	}
	
	@Override
	public StoreCategoryVO selectById(String id) throws StoreCategoryException{
		String sql= "select * from store_categories where store_category_id=?";
		List<StoreCategoryVO> storeCategories =null;
		try {
			storeCategories=jTemp.query(sql, new RowMapper<StoreCategoryVO>() {

				@Override
				public StoreCategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					String id= rs.getString("Store_category_id");
					String name=rs.getString("store_category_name");
					StoreCategoryVO storeCategory = new StoreCategoryVO(id,name);
					return storeCategory;
				}
				
			},id);
		}
		catch(Exception e) {
			throw new StoreCategorySelectFailedException(e.getMessage());
		}
		return (storeCategories.size()>0? storeCategories.get(0):null);
	}

	@Override
	public List<StoreCategoryVO> selectAll() throws StoreCategoryException{
		String sql = "select * from store_categories";
		List<StoreCategoryVO> storeCategories= null;
		try {
		storeCategories = jTemp.query(sql,new RowMapper<StoreCategoryVO>() {

			@Override
			public StoreCategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				String id = rs.getString("store_category_id");
				String name= rs.getString("store_category_name");
				StoreCategoryVO storeCategory = new StoreCategoryVO(id,name);
				return storeCategory;
			}
			
		});
		}
		catch(Exception e) {
			throw new StoreCategorySelectFailedException(e.getMessage());
		}
		
		return (storeCategories.size()>0 ? storeCategories :null);
	}

}
