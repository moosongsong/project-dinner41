package kr.co.dinner41.service.qna;

import kr.co.dinner41.dao.QnADaoImpl;
import kr.co.dinner41.exception.QnAException;
import kr.co.dinner41.vo.QnAVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qnAViewService")
public class QnAViewServiceImpl implements QnAViewService{
    @Autowired
    private QnADaoImpl qnADao;

    @Override
    public QnAVO execute(int id) {
        QnAVO qnAVO = null;
        try {
            qnAVO = qnADao.selectById(id);
        } catch (QnAException e) {
            e.printStackTrace();
        }
        return qnAVO;
    }
}
