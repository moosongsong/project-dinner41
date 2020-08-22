package kr.co.dinner41.dao;

import kr.co.dinner41.exception.QnATypeException;
import kr.co.dinner41.vo.QnATypeVO;

import java.util.List;

public interface QnaTypeDao {
    void insert(QnATypeVO qnAType) throws QnATypeException;
    void delete(String id) throws QnATypeException;
    void update(String id, String name) throws QnATypeException;
    QnATypeVO selectById(String id) throws QnATypeException;
    List<QnATypeVO> selectAll() throws QnATypeException;
}
