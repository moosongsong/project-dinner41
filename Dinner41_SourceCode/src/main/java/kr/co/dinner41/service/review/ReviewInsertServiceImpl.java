package kr.co.dinner41.service.review;

import kr.co.dinner41.command.ReviewInsertCommand;
import kr.co.dinner41.dao.ReviewDaoImpl;
import kr.co.dinner41.dao.StoreDaoImpl;
import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.exception.ReviewInsertException;
import kr.co.dinner41.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewInsertService")
public class ReviewInsertServiceImpl implements ReviewInsertService {
    @Autowired
    private ReviewDaoImpl reviewDao;

    @Autowired
    private StoreDaoImpl storeDao;

    @Override
    public void execute(ReviewInsertCommand command, UserVO user, int orderId) {
        StoreVO store = new StoreVO();
        try {
            store.setId(reviewDao.getStoreIdForReview(orderId));
        } catch (ReviewException e) {
            e.printStackTrace();
        }

        ReviewVO review = new ReviewVO();
        review.setStore(store);
        review.setContent(command.getContent());

        int score = Integer.parseInt(command.getScore());
        review.setScore(score);
        review.setOrder_id(orderId);

        review.setUser(user);

        try {
            reviewDao.insert(review);
        } catch (ReviewInsertException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StoreVO getStore(int orderId) {
        int storeId = 0;
        try {
            storeId = reviewDao.getStoreIdForReview(orderId);
        } catch (ReviewException e) {
            e.printStackTrace();
        }
        return storeDao.selectById(storeId);
    }

    @Override
    public List<ReveiwMenuVO> getMenus(int orderId) {
        List<ReveiwMenuVO> list = null;
        try {
            list = reviewDao.getMenus(orderId);
        } catch (ReviewException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isHaveReview(int orderId) {
        try {
            return reviewDao.isHaveReview(orderId);
        } catch (ReviewException e) {
            e.printStackTrace();
        }
        return true;
    }
}
