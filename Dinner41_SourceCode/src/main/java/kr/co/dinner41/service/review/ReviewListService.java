package kr.co.dinner41.service.review;

import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.ReviewVO;
import kr.co.dinner41.vo.StoreVO;

import java.util.List;

public interface ReviewListService {
    List<ReviewVO> execute(int storeId, int page);
    List<PageVO> getPages(int nowPage, int storeId);
    StoreVO getStore(int storeId);
    double getTotalScore(int storeId);
}
