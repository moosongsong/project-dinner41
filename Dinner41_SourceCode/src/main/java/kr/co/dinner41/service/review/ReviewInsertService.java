package kr.co.dinner41.service.review;

import kr.co.dinner41.command.ReviewInsertCommand;
import kr.co.dinner41.vo.OrderVO;
import kr.co.dinner41.vo.ReveiwMenuVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

import java.util.List;

public interface ReviewInsertService {
    void execute(ReviewInsertCommand command, UserVO user, int orderId);
    StoreVO getStore(int orderId);
    List<ReveiwMenuVO> getMenus(int orderId);
    boolean isHaveReview(int orderId);
}
