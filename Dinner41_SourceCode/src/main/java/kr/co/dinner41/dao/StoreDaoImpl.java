package kr.co.dinner41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.co.dinner41.mapper.MenuMapper;
import kr.co.dinner41.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.exception.store.StoreDeleteFailedException;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.exception.store.StoreInsertFailedException;
import kr.co.dinner41.exception.store.StoreSelectFailedException;
import kr.co.dinner41.exception.store.StoreUpdateFailedException;
import kr.co.dinner41.mapper.StoreMapper;

@Repository("storeDao")
public class StoreDaoImpl implements StoreDao {
	@Autowired
	private JdbcTemplate jTemp;
	
	@Autowired
	@Qualifier("reviewDao")
	ReviewDao reviewDao;
	
	private int searchDistance=1; //1km검색

	@Override
	public int insert(StoreVO store) throws StoreException {
		String sql = "insert into stores values (default,?,?,?,?,?,?,?,?,?,?,?,?,?,default,?)";
		int result=0;
		try {
			int userId= store.getUser().getId();//1
			String storeCategoryId=store.getCategory().getId();//2
			int storeStateId=1; // 등록시 신청 상태로 등록요청
			String storeBusinessNumber=store.getBusinessNumber();//4
			String storeName= store.getName();//5
			String storeAddress=store.getAddress();//6
			String storeSubAddress=store.getSubAddress();//7
			double storeLatitude=store.getLatitude();//8
			double storeLongitude=store.getLongitude();//9
			String storePhone=store.getPhone();//10
			String storeOperateTime=store.getOperateTime();//11
			String storePhoto=store.getPhoto();//12
			String storeIntroduction=store.getIntroduction();//13
			//OpenState storeOpenState = store.getOpenState();//
			String storePayNumber= store.getPayNumber();//14
			
			result= jTemp.update(sql,userId,storeCategoryId,storeStateId,storeBusinessNumber,storeName,storeAddress,storeSubAddress,
								storeLatitude,storeLongitude,storePhone,storeOperateTime,storePhoto,storeIntroduction,storePayNumber);
		}
		catch(Exception e) {
			throw new StoreInsertFailedException(e.getMessage());
		}
		if(result==0) {
			throw new StoreInsertFailedException("매장 등록을 시도하였으나 영향받은 행이 없습니다.");
		}
		return result;
	}
	@Override
	public void deleteByStore(int id) throws StoreException {
		String sql = "update stores set store_state_id=5 where store_id=?";
		int result=0;
		try {
			result=jTemp.update(sql,id);
		}
		catch(Exception e) {
			throw new StoreDeleteFailedException(e.getMessage());
		}
		if(result==0){
			throw new StoreDeleteFailedException("매장 삭제를 시도하였으나 영향받은 행이 없습니다.");
		}
	}
	
	@Override
	public void deleteByManager(int storeId, UserVO manager, String content) throws StoreException{
		int deleteCode = 6;
		String sql1 = "update stores set store_state_id = ? WHERE store_id = ?;";
		jTemp.update(sql1, deleteCode,storeId);

		final String sql2 = "INSERT INTO logs VALUES (DEFAULT, ?, ?, CURRENT_TIMESTAMP);";
		KeyHolder holder = new GeneratedKeyHolder();
		final String[] str = {"log_id"};
		jTemp.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql2, str);
				pstmt.setInt(1, storeId);
				pstmt.setInt(2, deleteCode);
				return pstmt;
			}
		}, holder);
		Number key= holder.getKey();
		int primaryID = key.intValue();

		String sql3 = "INSERT INTO log_to_managers VALUES (?, ?);";
		jTemp.update(sql3, primaryID, manager.getId());

		String sql4 = "INSERT INTO log_to_messages VALUES (?, ?);";
		jTemp.update(sql4, primaryID, content);
	}

	@Override
	public int update(StoreVO store) throws StoreException {
		String sql= "update stores set store_category_id=?,store_state_id=?, store_business_number=?,store_name=?, "
				+	"store_address=?, store_sub_address=?,store_latitude=?,store_longitude=?,store_phone=?,store_operate_time=?, "
				+	"store_photo=?, store_introduction=? " 
				+	"where store_id=?";
		int result=0;
		try {
			//int userId= store.getUser().getId();//1
			String storeCategoryId=store.getCategory().getId();//2
			int storeStateId=store.getState().getId();//3
			String storeBusinessNumber=store.getBusinessNumber();//4
			
			String storeName= store.getName();//5
			String storeAddress=store.getAddress();//6
			String storeSubAddress=store.getSubAddress();//7
			double storeLatitude=store.getLatitude();//8
			double storeLongitude=store.getLongitude();//9
			String storePhone=store.getPhone();//10
			String storeOperateTime=store.getOperateTime();//11
			String storePhoto=store.getPhoto();//12
			String storeIntroduction=store.getIntroduction();//13
			//OpenState storeOpenState = store.getOpenState();//14
			//String storePayNumber= store.getPayNumber();//15
			int storeId=store.getId(); //16
			result= jTemp.update(sql,storeCategoryId,storeStateId,storeBusinessNumber,storeName,storeAddress,storeSubAddress,
					storeLatitude,storeLongitude,storePhone,storeOperateTime,storePhoto,storeIntroduction,storeId);
		}
		catch(Exception e) {
			throw new StoreUpdateFailedException(e.getMessage());
		}
		if(result==0) {
			throw new StoreUpdateFailedException("매장 수정을 시도하였으나 영향받은 행이 없습니다.");
		}
		return result;
	}
	
	@Override
	public void switchOpenState(int storeId,OpenState openState) throws StoreException{
		String sql = "update stores set store_open_state=? where store_id=?";
		int result =0;
		try {
			result= jTemp.update(sql,openState.name(),storeId);
		}
		catch(Exception e) {
			throw new StoreUpdateFailedException(e.getMessage());
		}
		
	}
	

	@Override
	public StoreVO selectById(int id) throws StoreException {
		String sql = "select * from store_view where store_id=? order by store_id DESC";
		List<StoreVO> stores =null;
		try {
			stores=jTemp.query(sql,new StoreMapper(),id);
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		
		return (stores.size()>0?stores.get(0):null);
	}
	
	
	
	@Override
	public StoreVO selectByUserId(int userId) throws StoreException{
		String sql = "select * from store_view where user_id=? order by store_id DESC";
		List<StoreVO> stores =null;
		try {
			stores=jTemp.query(sql,new StoreMapper(),userId);
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0?stores.get(0):null);
	}

	@Override
	public List<StoreVO> selectByCategoryName(String categoryName, double userLatitude, double userLongitude,int page, int pageSize) throws StoreException {
		int startPos = (page-1)*pageSize;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select *,(6371*acos(cos(radians("+userLatitude+"))*cos(radians(store_latitude))*cos(radians(store_longitude)-");
		sb.append("radians("+userLongitude+"))+sin(radians("+userLatitude+"))*sin(radians(store_latitude))))");
		sb.append(" AS distance from (select * from store_view where store_category_name like '"+categoryName+"' and store_state_id=2) AS viewByCategory "); 
		sb.append(" HAVING distance <=1 order by distance limit "+startPos+","+pageSize);
		
		String sql = sb.toString();
		List<StoreVO> stores = null;
		try {
			stores=jTemp.query(sql, new StoreMapper());
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0? stores: null);
	}
	
	@Override
	public List<StoreVO> selectByCategoryNameOnMap(String categoryName, double userLatitude, double userLongitude) throws StoreException {
		StringBuffer sb = new StringBuffer();
		sb.append("select *,(6371*acos(cos(radians("+userLatitude+"))*cos(radians(store_latitude))*cos(radians(store_longitude)-");
		sb.append("radians("+userLongitude+"))+sin(radians("+userLatitude+"))*sin(radians(store_latitude))))");
		sb.append(" AS distance from (select * from store_view where store_category_name like ? and store_state_id=2 ) AS viewByCategory "); 
		sb.append(" HAVING distance <=1 order by distance limit 0,300");
		
		if(categoryName.equals("ALL")) {
			categoryName="%";
		}
		
		String sql = sb.toString();
		List<StoreVO> stores = null;
		try {
			stores=jTemp.query(sql, new StoreMapper(),categoryName);
			System.out.println("dao:"+stores);
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0? stores: null);
	}

	@Override
	public List<StoreVO> selectByStateName(String stateName, int page, int pageSize) throws StoreException {
		int startPos = (page-1)*pageSize;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from store_view ");
		sb.append("where store_state_name like '"+stateName+"' ");
		sb.append("order by store_id ASC limit "+startPos+","+pageSize);
		
		String sql = sb.toString();
		List<StoreVO> stores = null;
		try {
			stores=jTemp.query(sql, new StoreMapper());
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0? stores: null);
	}
	
	@Override
	public List<StoreVO> selectByStateNameAndName(String stateName,String name,int page,int pageSize) throws StoreException{
		int startPos = (page-1)*pageSize;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from store_view ");
		sb.append("where store_state_name like '"+stateName+"' and store_name like '%"+name+"%' ");
		sb.append("order by store_id ASC limit "+startPos+","+pageSize);
		
		String sql = sb.toString();
		List<StoreVO> stores = null;
		try {
			stores=jTemp.query(sql, new StoreMapper());
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0? stores: null);
	}

	@Override
	public StoreVO selectByBusinessNumber(String businessNumber) throws StoreException {
		String sql = "select * from store_view where store_business_number=? and store_state_id=2";
		List<StoreVO> stores =null;
		try {
			stores=jTemp.query(sql, new StoreMapper(),businessNumber);
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0?stores.get(0):null);
	}

	@Override
	public List<StoreVO> selectByName(String name,double userLatitude, double userLongitude, int page, int pageSize) throws StoreException {
		int startPos = (page-1)*pageSize;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select *,(6371*acos(cos(radians("+userLatitude+"))*cos(radians(store_latitude))*cos(radians(store_longitude)-");
		sb.append("radians("+userLongitude+"))+sin(radians("+userLatitude+"))*sin(radians(store_latitude))))");
		sb.append(" AS distance from (select * from store_view where store_name like '%"+name+"%' and store_state_id=2) AS viewByCategory "); 
		sb.append(" HAVING distance <=1 order by distance limit "+startPos+","+pageSize);
		
		String sql = sb.toString();
		List<StoreVO> stores = null;
		try {
			stores=jTemp.query(sql, new StoreMapper());
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0? stores: null);
	}

	@Override
	public List<StoreVO> selectByLocation(double userLatitude, double userLongitude) throws StoreException {
		StringBuffer sb = new StringBuffer();
		sb.append("select *,(6371*acos(cos(radians("+userLatitude+"))*cos(radians(store_latitude))*cos(radians(store_longitude)-");
		sb.append("radians("+userLongitude+"))+sin(radians("+userLatitude+"))*sin(radians(store_latitude))))");
		sb.append("AS distance from store_view where store_state_id=2 HAVING distance <=1 order by distance limit 0,300");
		
		String sql = sb.toString();
		List<StoreVO> stores = null;
		try {
			stores=jTemp.query(sql, new StoreMapper());
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0? stores: null);
	}

	@Override
	public List<StoreVO> selectByOpenState(OpenState openState, int page, int pageSize) throws StoreException {
		int startPos = (page-1)*pageSize;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from store_view ");
		sb.append("where store_open_state='"+openState+"' and store_state_id=2 ");
		sb.append("order by store_id ASC limit "+startPos+","+pageSize);
		
		String sql = sb.toString();
		List<StoreVO> stores = null;
		try {
			stores=jTemp.query(sql, new StoreMapper());
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0? stores: null);
	}

	@Override
	public List<StoreVO> selectAll(int page, int pageSize) throws StoreException {
		int startPos = (page-1)*pageSize;
		StringBuffer sb = new StringBuffer();
		sb.append("select * from store_view ");
		sb.append("order by store_id desc limit "+startPos+","+pageSize);
		
		String sql = sb.toString();
		List<StoreVO> stores =null;
		try {
			stores=jTemp.query(sql, new StoreMapper());
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (stores.size()>0? stores:null);
	}
	
	public List<StoreListByUserViewVO> selectViewByCategoryName(String categoryName, double userLatitude, double userLongitude,int page, int pageSize) throws StoreException{
		int startPos = (page-1)*pageSize;
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct store_id,store_name,store_photo,");
		sb.append("(6371*acos(cos(radians("+userLatitude+"))*cos(radians(store_latitude))*cos(radians(store_longitude) - ");
		sb.append("radians("+userLongitude+"))+sin(radians("+userLatitude+"))*sin(radians(store_latitude)))) AS distance ");
		sb.append("from store_view where store_category_name like '%"+categoryName+"%' and store_state_id=2 ");
		sb.append(" HAVING distance <=1 order by distance limit "+startPos+","+pageSize);
		
		String sql = sb.toString();
		List<StoreListByUserViewVO> storeListByUsers =null;
		try {
			storeListByUsers=jTemp.query(sql, new RowMapper<StoreListByUserViewVO>() {

				@Override
				public StoreListByUserViewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					int storeId = rs.getInt("store_id");
					String storeName = rs.getString("store_name");
					String storePhoto= rs.getString("store_photo");
					double distance = rs.getDouble("distance");
					distance = distance*1000;
					int intDistance = (int)distance;
					double reviewScoreAvg=0.0;
					try {
						reviewScoreAvg = reviewDao.getAverageScore(storeId);
						reviewScoreAvg = Double.parseDouble(String.format("%.2f",reviewScoreAvg));
					} catch (ReviewException e) {
						e.printStackTrace();
					}
					StoreListByUserViewVO storeListByUsers = new StoreListByUserViewVO(storeId,storeName,storePhoto,intDistance,reviewScoreAvg);
					return storeListByUsers;
				}
				
			});
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (storeListByUsers.size()>0? storeListByUsers:null);
	}

	
	public List<StoreListByUserViewVO> selectViewByStoreNameOrMenuName(String keyword, double userLatitude, double userLongitude,int page, int pageSize) throws StoreException{
		int startPos = (page-1)*pageSize;
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct store_id,store_name,store_photo,");
		sb.append("(6371*acos(cos(radians("+userLatitude+"))*cos(radians(store_latitude))*cos(radians(store_longitude) - ");
		sb.append("radians("+userLongitude+"))+sin(radians("+userLatitude+"))*sin(radians(store_latitude)))) AS distance ");
		sb.append("from (select * from stores inner join menus using(store_id) ");
		sb.append("where (store_name like '%"+keyword+"%' or menu_tag like '%"+keyword+"%') and store_state_id=2 and store_open_state='OPEN') AS View_1 ");
		sb.append("having distance <= 1 order by distance limit "+startPos+","+pageSize);

		String sql = sb.toString();
		List<StoreListByUserViewVO> storeListByUsers =null;
		try {
			storeListByUsers=jTemp.query(sql, new RowMapper<StoreListByUserViewVO>() {

				@Override
				public StoreListByUserViewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					int storeId = rs.getInt("store_id");
					String storeName = rs.getString("store_name");
					String storePhoto= rs.getString("store_photo");
					double distance = rs.getDouble("distance");
					distance = distance*1000;
					int intDistance = (int)distance;
					double reviewScoreAvg=0.0;
					try {
						reviewScoreAvg = reviewDao.getAverageScore(storeId);
						reviewScoreAvg = Double.parseDouble(String.format("%.2f",reviewScoreAvg));
					} catch (ReviewException e) {
						e.printStackTrace();
					}
					StoreListByUserViewVO storeListByUsers = new StoreListByUserViewVO(storeId,storeName,storePhoto,intDistance,reviewScoreAvg);
					return storeListByUsers;
				}
				
			});
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (storeListByUsers.size()>0? storeListByUsers:null);
	}

	@Override
	public List<MenuVO> getMenus(int storeId) throws StoreException {
		List<MenuVO> list = null;
		String sql = "select * from menu_view WHERE store_id = ?";
		list = jTemp.query(sql, new MenuMapper(), storeId);
		return list;
	}

	@Override
	public void approve(int storeId, UserVO manager) throws StoreException {
		int approveCode = 2;
		String sql1 = "update stores set store_state_id = ? WHERE store_id = ?;";
		jTemp.update(sql1, approveCode,storeId);

		final String sql2 = "INSERT INTO logs VALUES (DEFAULT, ?, ?, CURRENT_TIMESTAMP);";
		KeyHolder holder = new GeneratedKeyHolder();
		final String[] str = {"log_id"};
		jTemp.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql2, str);
				pstmt.setInt(1, storeId);
				pstmt.setInt(2, approveCode);
				return pstmt;
			}
		}, holder);
		Number key= holder.getKey();
		int primaryID = key.intValue();

		String sql3 = "INSERT INTO log_to_managers VALUES (?, ?);";
		jTemp.update(sql3, primaryID, manager.getId());
	}

	@Override
	public void block(int storeId, UserVO manager, String content) throws StoreException {
		int blockCode = 4;
		String sql1 = "update stores set store_state_id = ? WHERE store_id = ?;";
		jTemp.update(sql1, blockCode,storeId);

		final String sql2 = "INSERT INTO logs VALUES (DEFAULT, ?, ?, CURRENT_TIMESTAMP);";
		KeyHolder holder = new GeneratedKeyHolder();
		final String[] str = {"log_id"};
		jTemp.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql2, str);
				pstmt.setInt(1, storeId);
				pstmt.setInt(2, blockCode);
				return pstmt;
			}
		}, holder);
		Number key= holder.getKey();
		int primaryID = key.intValue();

		String sql3 = "INSERT INTO log_to_managers VALUES (?, ?);";
		jTemp.update(sql3, primaryID, manager.getId());

		String sql4 = "INSERT INTO log_to_messages VALUES (?, ?);";
		jTemp.update(sql4, primaryID, content);
	}

	@Override
	public void reject(int storeId, UserVO manager, String content) throws StoreException {
		int rejectCode = 3;
		String sql1 = "update stores set store_state_id = ? WHERE store_id = ?;";
		jTemp.update(sql1, rejectCode,storeId);

		final String sql2 = "INSERT INTO logs VALUES (DEFAULT, ?, ?, CURRENT_TIMESTAMP);";
		KeyHolder holder = new GeneratedKeyHolder();
		final String[] str = {"log_id"};
		jTemp.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql2, str);
				pstmt.setInt(1, storeId);
				pstmt.setInt(2, rejectCode);
				return pstmt;
			}
		}, holder);
		Number key= holder.getKey();
		int primaryID = key.intValue();

		String sql3 = "INSERT INTO log_to_managers VALUES (?, ?);";
		jTemp.update(sql3, primaryID, manager.getId());

		String sql4 = "INSERT INTO log_to_messages VALUES (?, ?);";
		jTemp.update(sql4, primaryID, content);
	}

	@Override
	public int getTotalCount(String stateName, String name) throws StoreException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from store_view ");
		sb.append("where store_state_name like '"+stateName+"' and store_name like '%"+name+"%' ");

		String sql = sb.toString();
		List<StoreVO> stores = null;
		try {
			stores=jTemp.query(sql, new StoreMapper());
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (Math.max(stores.size(), 0));
	}


	@Override
	public int getTotalCountByCategoryName(String categoryName, double userLatitude, double userLongitude, int page, int pageSize) throws StoreException {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct store_id,store_name,store_photo,");
		sb.append("(6371*acos(cos(radians("+userLatitude+"))*cos(radians(store_latitude))*cos(radians(store_longitude) - ");
		sb.append("radians("+userLongitude+"))+sin(radians("+userLatitude+"))*sin(radians(store_latitude)))) AS distance ");
		sb.append("from store_view where store_category_name like '%"+categoryName+"%' and store_state_id=2 ");
		sb.append(" HAVING distance <=1");

		String sql = sb.toString();
		List<StoreListByUserViewVO> storeListByUsers =null;
		try {
			storeListByUsers=jTemp.query(sql, new RowMapper<StoreListByUserViewVO>() {

				@Override
				public StoreListByUserViewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					int storeId = rs.getInt("store_id");
					String storeName = rs.getString("store_name");
					String storePhoto= rs.getString("store_photo");
					double distance = rs.getDouble("distance");
					distance = distance*1000;
					int intDistance = (int)distance;
					double reviewScoreAvg=0.0;
					try {
						reviewScoreAvg = reviewDao.getAverageScore(storeId);
						reviewScoreAvg = Double.parseDouble(String.format("%.2f",reviewScoreAvg));
					} catch (ReviewException e) {
						e.printStackTrace();
					}
					StoreListByUserViewVO storeListByUsers = new StoreListByUserViewVO(storeId,storeName,storePhoto,intDistance,reviewScoreAvg);
					return storeListByUsers;
				}

			});
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (Math.max(storeListByUsers.size(), 0));
	}

	@Override
	public int getTotalCountByStoreNameOrMenuName(String keyword, double userLatitude, double userLongitude, int page, int pageSize) throws StoreException {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct store_id,store_name,store_photo,");
		sb.append("(6371*acos(cos(radians("+userLatitude+"))*cos(radians(store_latitude))*cos(radians(store_longitude) - ");
		sb.append("radians("+userLongitude+"))+sin(radians("+userLatitude+"))*sin(radians(store_latitude)))) AS distance ");
		sb.append("from (select * from stores inner join menus using(store_id) ");
		sb.append("where (store_name like '%"+keyword+"%' or menu_tag like '%"+keyword+"%') and store_state_id=2 and store_open_state='OPEN') AS View_1 ");
		sb.append("having distance <= 1");

		String sql = sb.toString();
		List<StoreListByUserViewVO> storeListByUsers =null;
		try {
			storeListByUsers=jTemp.query(sql, new RowMapper<StoreListByUserViewVO>() {

				@Override
				public StoreListByUserViewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					int storeId = rs.getInt("store_id");
					String storeName = rs.getString("store_name");
					String storePhoto= rs.getString("store_photo");
					double distance = rs.getDouble("distance");
					distance = distance*1000;
					int intDistance = (int)distance;
					double reviewScoreAvg=0.0;
					try {
						reviewScoreAvg = reviewDao.getAverageScore(storeId);
						reviewScoreAvg = Double.parseDouble(String.format("%.2f",reviewScoreAvg));
					} catch (ReviewException e) {
						e.printStackTrace();
					}
					StoreListByUserViewVO storeListByUsers = new StoreListByUserViewVO(storeId,storeName,storePhoto,intDistance,reviewScoreAvg);
					return storeListByUsers;
				}

			});
		}
		catch(Exception e) {
			throw new StoreSelectFailedException(e.getMessage());
		}
		return (Math.max(storeListByUsers.size(), 0));
	}

}
