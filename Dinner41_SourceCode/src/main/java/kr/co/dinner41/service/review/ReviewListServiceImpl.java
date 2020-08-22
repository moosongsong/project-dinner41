package kr.co.dinner41.service.review;

import kr.co.dinner41.dao.ReviewDaoImpl;
import kr.co.dinner41.dao.StoreDaoImpl;
import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.exception.ReviewSelectException;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.ReviewVO;
import kr.co.dinner41.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("reviewListService")
public class ReviewListServiceImpl implements ReviewListService {
    @Autowired
    private ReviewDaoImpl reviewDao;

    @Autowired
    private StoreDaoImpl storeDao;

    public static final int PAGE_SIZE = 10;
    public static final int NUMBER_OF_PAGE_IN_ON_PAGE = 4;

    @Override
    public List<ReviewVO> execute(int storeId, int page) {
        List<ReviewVO> list = null;
        int totalRecord = 0;

        try {
            totalRecord = reviewDao.getTotalRecordByStoreId(storeId);
        } catch (ReviewException e) {
            e.printStackTrace();
        }

        int totalPage = totalRecord/PAGE_SIZE +1;
        if (page > totalPage){
            page = totalPage;
        }
        if (page<1){
            page = 1;
        }

        try {
            list = reviewDao.selectedByStoreId(page, PAGE_SIZE, storeId);
        } catch (ReviewSelectException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<PageVO> getPages(int nowPage, int storeId) {
        List<PageVO> list = new ArrayList<>();
        int totalRecord = 0;

        try {
            totalRecord = reviewDao.getTotalRecordByStoreId(storeId);
        } catch (ReviewException e) {
            e.printStackTrace();
        }

        int totalPage = totalRecord/PAGE_SIZE;
        if ((totalRecord%PAGE_SIZE) != 0){
            totalPage = totalPage+1;
        }

        if (nowPage > totalPage){
            nowPage = totalPage;
        }
        if (nowPage<1){
            nowPage = 1;
        }

        int startPoint = nowPage/NUMBER_OF_PAGE_IN_ON_PAGE;
        int startPage = startPoint*NUMBER_OF_PAGE_IN_ON_PAGE + 1;

        int endPage = startPage + NUMBER_OF_PAGE_IN_ON_PAGE - 1;
        if (endPage > totalPage){
            endPage = totalPage;
        }

        int first=0;
        if (startPage>1){
            first = startPage-1;
        }
        if (startPage<=1){
            first = 1;
        }
        PageVO firstPage = new PageVO("<<", first);

        int last = 0;
        if (endPage<totalPage){
            last = endPage +1;
        }
        if (endPage >= totalPage){
            last = endPage;
        }
        PageVO lastPage = new PageVO(">>", last);

        list.add(firstPage);
        for (int i=startPage; i<=endPage; i++){
            PageVO pageVO = new PageVO(""+i, i);
            list.add(pageVO);
        }
        list.add(lastPage);

        return list;
    }

    @Override
    public StoreVO getStore(int storeId) {
        return storeDao.selectById(storeId);
    }

    @Override
    public double getTotalScore(int storeId) {
        double result = 0;
        try {
            result = reviewDao.getAverageScore(storeId);
        } catch (ReviewException e) {
            e.printStackTrace();
        }
        return result;
    }
}
