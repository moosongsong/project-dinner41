package kr.co.dinner41.dao;

import kr.co.dinner41.exception.QnAException;
import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.vo.ReveiwMenuVO;
import kr.co.dinner41.vo.ReviewVO;

import java.util.List;

public interface ReviewDao {
    void insert(ReviewVO review) throws ReviewException;
    void delete(int id) throws ReviewException;
    ReviewVO selectedById(int id) throws ReviewException;
    List<ReviewVO> selectedByStoreId(int page, int pageSize, int storeId) throws ReviewException;
    List<ReviewVO> selectedAll() throws ReviewException;
    int getTotalRecordByStoreId(int storeId) throws ReviewException;
    double getAverageScore(int storeId) throws ReviewException;
    int getStoreIdForReview(int orederId) throws ReviewException;
    List<ReveiwMenuVO> getMenus(int orderId) throws ReviewException;
    boolean isHaveReview(int orderId) throws  ReviewException;
}
