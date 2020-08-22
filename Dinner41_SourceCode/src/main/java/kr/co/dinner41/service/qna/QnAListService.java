package kr.co.dinner41.service.qna;

import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.QnAVO;
import kr.co.dinner41.vo.UserVO;

import java.util.List;

public interface QnAListService {
    List<QnAVO> execute(UserVO user, String qnaType, int page);
    List<QnAVO> execute(String qnaType, int page);
    List<PageVO> getPages(int nowPage, String qnaType);
    List<PageVO> getPages(int nowPage, String qnaType, UserVO user);
}
