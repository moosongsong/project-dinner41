package kr.co.dinner41.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.dinner41.exception.menu.MenuDeleteFailedException;
import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.exception.menu.MenuInsertFailedException;
import kr.co.dinner41.exception.menu.MenuSelectFailedException;
import kr.co.dinner41.exception.menu.MenuUpdateFailedException;
import kr.co.dinner41.mapper.MenuMapper;
import kr.co.dinner41.service.menu.MenuListByUserServiceImpl;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private JdbcTemplate jTemp;

	@Override
	public int insert(MenuVO menu, StoreVO store) throws MenuException {
		String sql = "INSERT INTO menus VALUES(?,?,?,?,?,?,?,?,?,?,default)";
		int result=0;
		try {
			result=jTemp.update(sql, store.getId(), menu.getId(), menu.getOfferType().getId(), menu.getTag(), menu.getName(),
					menu.getPrice(), menu.getAmount(), menu.getDescription(), menu.getNotice(), menu.getPhoto());
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			throw new MenuInsertFailedException();
		}
		return result;
	}

	@Override
	public void delete(int menuId, int storeId) throws MenuException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String now = sdf.format(calendar.getTime());

		String sql = "UPDATE menus SET menu_remove_date = '" + now + "' WHERE menu_id = " + menuId + " AND store_id ="
				+ storeId;

		int result = 0;

		try {
			result = jTemp.update(sql);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MenuDeleteFailedException();
		}

		if (result == 0) {
			throw new MenuDeleteFailedException("매장 삭제실패 ");
		}

	}

	@Override
	public int update(MenuVO menu, StoreVO store) throws MenuException {
		String sql = "UPDATE menu_view SET offer_type_id = ?, menu_name = ?, menu_price = ?, menu_amount = ?, menu_description = ?, menu_notice = ?,menu_photo = ? WHERE menu_id = ? AND store_id=? ";

		int result = 0;

		try {
			result = jTemp.update(sql, menu.getOfferType().getId(), menu.getName(), menu.getPrice(), menu.getAmount(),
					menu.getDescription(), menu.getNotice(), menu.getPhoto(), menu.getId(), store.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MenuUpdateFailedException();
		}
		if (result == 0) {
			throw new MenuUpdateFailedException();
		}
		return result;
	}

	@Override
	public MenuVO selectByMenuIdStoreId(int menuId, int storeId) throws MenuException {
		List<MenuVO> list;
		String sql = "SELECT * FROM menu_view " + "WHERE menu_id = ? AND store_id =?";
		list = jTemp.query(sql, new MenuMapper(), menuId, storeId);
		return (list.size() == 0 ? null : list.get(0));
	}

	@Override
	public MenuVO selectByMenuId(int menuId) throws MenuException {
		List<MenuVO> list;
		String sql = "SELECT * FROM menu_view " + "WHERE menu_id = ? ";
		list = jTemp.query(sql, new MenuMapper(), menuId);
		return (list.size() == 0 ? null : list.get(0));
	}

	@Override
	public List<MenuVO> selectAll(int page, int pageSize, String condition, String word) throws MenuException {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM menus ");

		switch (condition) {

		case MenuListByUserServiceImpl.CONDITION_TITLE:
			sb.append("WHERE menu_name LIKE '%" + word + "%' AND menu_remove_date is null ");
			sb.append("AND menu_amount > 0 ");
			break;

		case MenuListByUserServiceImpl.CONDITION_DESCRIPTION:
			sb.append("WHERE menu_description LIKE '%" + word + "%' AND menu_remove_date is null ");
			sb.append("AND menu_amount > 0 ");
			break;

		default:
			sb.append("WHERE (menu_name LIKE '%" + word + "%' ");
			sb.append("OR menu_description LIKE '%" + word + "%') ");
			sb.append("AND menu_remove_date is null ");
			break;
		}

		List<MenuVO> list;

		int startPoint = (page - 1) * pageSize;
		sb.append("ORDER BY menu_id DESC ");
		sb.append("LIMIT " + startPoint + "," + pageSize);

		String sql = sb.toString();

		list = jTemp.query(sql, new MenuMapper(), startPoint, pageSize);
		return list;

	}

	@Override
	public List<MenuVO> selectByStoreId(int storeId, int page, int pageSize) throws MenuException {

		int startPoint = (page - 1) * pageSize;

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM menu_view WHERE store_id = '" + storeId + "' ");
		sb.append("AND menu_remove_date is null ");
		sb.append("ORDER BY menu_id DESC LIMIT " + startPoint + "," + pageSize);

		String sql = sb.toString();

		List<MenuVO> menus = null;

		try {
			menus = jTemp.query(sql, new MenuMapper());
		} catch (Exception e) {
			throw new MenuSelectFailedException(e.getMessage());
		}
		return (menus.size() > 0 ? menus : null);
	}

	@Override
	public List<MenuVO> userSelectByStoreId(int storeId, int page, int pageSize) throws MenuException {

		int startPoint = (page - 1) * pageSize;

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM menu_view WHERE store_id = '" + storeId + "' ");
		sb.append("AND menu_remove_date is null ");
		sb.append("AND menu_amount > 0 ");
		sb.append("ORDER BY menu_id DESC LIMIT " + startPoint + "," + pageSize);

		String sql = sb.toString();
		List<MenuVO> menus = null;

		try {
			menus = jTemp.query(sql, new MenuMapper());
		} catch (Exception e) {
			throw new MenuSelectFailedException(e.getMessage());
		}
		return (menus.size() > 0 ? menus : null);
	}

	@Override
	public int getLastInsertId(int storeId) throws SQLException {

		String sql = "SELECT COUNT(menu_id) AS count FROM menus WHERE store_id =?";

		List<Integer> count = null;
		try {
			count = jTemp.query(sql, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt("count");
				}
			}, storeId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (count.size() == 0 ? null : count.get(0));
	}

	public void updateAmount(int menuId, int storeId, int count) throws MenuUpdateFailedException {
		String sql = "UPDATE menu_view SET menu_amount = ? WHERE menu_id = ? AND store_id=? ";

		int result = 0;

		try {
			result = jTemp.update(sql, count, menuId, storeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MenuUpdateFailedException();
		}
		if (result == 0) {
			throw new MenuUpdateFailedException();
		}

	}
	
	@Override
	public int getTotalRecord() throws MenuException {
		 String sql="SELECT count(*) as num from menus;";
	        List<Integer> list = jTemp.query(sql, new RowMapper<Integer>() {
	            @Override
	            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	                return rs.getInt("num");
	            }
	        });

	        return (list.size() == 0? 0:list.get(0));
	}
	
	@Override
	public int getTotalRecord(UserVO user) throws MenuException {
		 String sql="SELECT count(*) as num from menus WHERE user_id = ?;";
	        List<Integer> list = jTemp.query(sql, new RowMapper<Integer>() {
	            @Override
	            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	                return rs.getInt("num");
	            }
	        }, user.getId());

	        return (list.size() == 0? 0:list.get(0));
	}

	@Override
	public int getTotalRecord(int storeId) throws MenuException {
		 String sql="SELECT count(*) as num from menus WHERE store_id = ?;";
	        List<Integer> list = jTemp.query(sql, new RowMapper<Integer>() {
	            @Override
	            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	                return rs.getInt("num");
	            }
	        }, storeId);

	        return (list.size() == 0? 0:list.get(0));
	}

	@Override
	public int getTotalRecord(int storeId, UserVO user) throws MenuException {
		String sql = "SELECT count(*) as num from menus WHERE store_id LIKE ? AND user_id = ? AND menu_remove_date IS NULL;";
        List<Integer> list = jTemp.query(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("num");
            }
        }, storeId, user.getId());

        return (list.size() == 0? 0:list.get(0));
	}
}
