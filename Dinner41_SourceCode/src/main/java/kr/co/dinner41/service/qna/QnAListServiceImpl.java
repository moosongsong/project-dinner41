package kr.co.dinner41.service.qna;

import kr.co.dinner41.dao.QnADaoImpl;
import kr.co.dinner41.exception.QnAException;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.QnAVO;
import kr.co.dinner41.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("qnAListService")
public class QnAListServiceImpl implements QnAListService{
    @Autowired
    private QnADaoImpl qnADao;
    public static final int PAGE_SIZE = 10;
    public static final int NUMBER_OF_PAGE_IN_ONE_PAGE = 4;

    @Override
    public List<QnAVO> execute(UserVO user, String qnaType, int page) {
        List<QnAVO> list = null;

        int totalRecord = 0;

        if (qnaType.equals("ALL")){
            try {
                totalRecord = qnADao.getTotalRecord();
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }else if (qnaType.equals("DON")){
            try {
                totalRecord = qnADao.getTotalRecordDone();
            } catch (QnAException e) {
                e.printStackTrace();
            }
        } else {
            try {
                totalRecord = qnADao.getTotalRecord(qnaType);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }

        int totalPage = totalRecord/PAGE_SIZE +1;
        if (page > totalPage){
            page = totalPage;
        }
        if (page<1){
            page = 1;
        }

        if (qnaType.equals("ALL")){
            try {
                list = qnADao.selectAll(page, PAGE_SIZE, user);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        } else if (qnaType.equals("DON")){
            try {
                list = qnADao.selectAllDone(page, PAGE_SIZE, user);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        } else{
            try {
                list = qnADao.selectAll(page, PAGE_SIZE,qnaType, user);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<QnAVO> execute(String qnaType, int page) {
        List<QnAVO> list = null;

        int totalRecord = 0;

        if (qnaType.equals("ALL")){
            try {
                totalRecord = qnADao.getTotalRecord();
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }else if (qnaType.equals("DON")){
            try {
                totalRecord = qnADao.getTotalRecordDone();
            } catch (QnAException e) {
                e.printStackTrace();
            }
        } else {
            try {
                totalRecord = qnADao.getTotalRecord(qnaType);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }

        int totalPage = totalRecord/PAGE_SIZE +1;
        if (page > totalPage){
            page = totalPage;
        }
        if (page<1){
            page = 1;
        }

        if (qnaType.equals("ALL")){
            try {
                list = qnADao.selectAll(page, PAGE_SIZE);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        } else if (qnaType.equals("DON")){
            try {
                list = qnADao.selectAllDone(page, PAGE_SIZE);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        } else{
            try {
                list = qnADao.selectAll(page, PAGE_SIZE, qnaType);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<PageVO> getPages(int nowPage, String qnaType) {
        List<PageVO> list = new ArrayList<>();
        int totalRecord = 0;

        if (qnaType.equals("ALL")){
            try {
                totalRecord = qnADao.getTotalRecord();
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }else if (qnaType.equals("DON")){
            try {
                totalRecord = qnADao.getTotalRecordDone();
            } catch (QnAException e) {
                e.printStackTrace();
            }
        } else {
            try {
                totalRecord = qnADao.getTotalRecord(qnaType);
            } catch (QnAException e) {
                e.printStackTrace();
            }
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

        int startPoint = nowPage/ NUMBER_OF_PAGE_IN_ONE_PAGE;
        int startPage = startPoint* NUMBER_OF_PAGE_IN_ONE_PAGE + 1;

        int endPage = startPage + NUMBER_OF_PAGE_IN_ONE_PAGE - 1;
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
    public List<PageVO> getPages(int nowPage, String qnaType, UserVO user) {
        List<PageVO> list = new ArrayList<>();
        int totalRecord = 0;

        if (qnaType.equals("ALL")){
            try {
                totalRecord = qnADao.getTotalRecord(user);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }else if (qnaType.equals("DON")){
            try {
                totalRecord = qnADao.getTotalRecordDone(user);
            } catch (QnAException e) {
                e.printStackTrace();
            }
        }else{
            try {
                totalRecord = qnADao.getTotalRecord(qnaType, user);
            } catch (QnAException e) {
                e.printStackTrace();
            }
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
        int startPoint = nowPage/ NUMBER_OF_PAGE_IN_ONE_PAGE;
        int startPage = startPoint* NUMBER_OF_PAGE_IN_ONE_PAGE + 1;

        int endPage = startPage + NUMBER_OF_PAGE_IN_ONE_PAGE - 1;
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
}
