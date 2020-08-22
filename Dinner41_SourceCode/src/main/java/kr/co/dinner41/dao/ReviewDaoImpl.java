package kr.co.dinner41.dao;

import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.exception.ReviewInsertException;
import kr.co.dinner41.exception.ReviewSelectException;
import kr.co.dinner41.mapper.ReviewMapper;
import kr.co.dinner41.vo.ReveiwMenuVO;
import kr.co.dinner41.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("reviewDao")
public class ReviewDaoImpl implements ReviewDao{
    @Autowired
    private JdbcTemplate template;

    @Override
    public void insert(ReviewVO review) throws ReviewInsertException {
        String sql = "INSERT INTO reviews VALUES(DEFAULT, ?, ?, ?, ?, DEFAULT, ?);";
        template.update(sql, review.getStore().getId(), review.getUser().getId(), review.getContent(), review.getScore(), review.getOrder_id());
    }

    @Override
    public void delete(int id) throws ReviewException {
        return;
    }

    @Override
    public ReviewVO selectedById(int id) throws ReviewSelectException {
        List<ReviewVO> list;
        String sql = "SELECT * FROM review_view WHERE review_id = ?;";
        list = template.query(sql, new ReviewMapper(), id);
        return (list.size() == 0? null:list.get(0));
    }

    @Override
    public List<ReviewVO> selectedByStoreId(int page, int pageSize, int storeId) throws ReviewSelectException {
        List<ReviewVO> list;
        String sql = "SELECT * FROM review_view WHERE store_id = ? ORDER BY review_date DESC;";
        list = template.query(sql, new ReviewMapper(), storeId);
        return list;
    }

    @Override
    public List<ReviewVO> selectedAll() throws ReviewSelectException {
        List<ReviewVO> list;
        String sql = "SELECT * FROM review_view;";
        list = template.query(sql, new ReviewMapper());
        return list;
    }

    @Override
    public int getTotalRecordByStoreId(int storeId) throws ReviewException {
        List<Integer> list = template.query("select count(*) as num from reviews WHERE store_id LIKE ?;", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("num");
            }
        }, storeId);

        return (list.size() == 0? 0:list.get(0));
    }

    @Override
    public double getAverageScore(int storeId) throws ReviewException {
        List<Double> list = template.query("select AVG(review_score) as num from reviews WHERE store_id = ?;", new RowMapper<Double>() {
            @Override
            public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getDouble("num");
            }
        }, storeId);
        return (list.size() == 0? 0:list.get(0));
    }

    @Override
    public int getStoreIdForReview(int orederId) throws ReviewException {
        List<Integer> list = template.query("select distinct store_id from menu_to_orders WHERE order_id = ?;", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("store_id");
            }
        }, orederId);
        return (list.size() == 0? 0:list.get(0));
    }

    @Override
    public List<ReveiwMenuVO> getMenus(int orderId) throws ReviewException {
        String sql = "select menu_name, menu_to_order_amount, menu_price from menu_to_orders, menus WHERE order_id = ? AND menus.menu_id=menu_to_orders.menu_id;";
        List<ReveiwMenuVO> list = template.query(sql, new RowMapper<ReveiwMenuVO>() {
            @Override
            public ReveiwMenuVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                String menuName = rs.getString("menu_name");
                int menuAmount = rs.getInt("menu_to_order_amount");
                int menuPrice = rs.getInt("menu_price");
                return new ReveiwMenuVO(menuName, menuAmount, menuPrice,menuPrice*menuAmount);
            }
        }, orderId);
        return list;
    }

    @Override
    public boolean isHaveReview(int orderId) throws ReviewException {
        String sql ="select review_id from reviews WHERE order_id_temp = ?;";

        List<Integer> list = template.query(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("review_id");
            }
        }, orderId);
        return (list.size() == 0? false:true);
    }
}
