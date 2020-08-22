package kr.co.dinner41.service.qna;

import kr.co.dinner41.command.QnAInsertCommand;
import kr.co.dinner41.dao.QnADaoImpl;
import kr.co.dinner41.dao.QnATypeDaoImpl;
import kr.co.dinner41.exception.QnAException;
import kr.co.dinner41.exception.QnATypeSelectException;
import kr.co.dinner41.vo.QnATypeVO;
import kr.co.dinner41.vo.QnAVO;
import kr.co.dinner41.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qnAInsertService")
public class QnAInsertServiceImpl implements QnAInsertService{
    @Autowired
    private QnADaoImpl qnADao;

    @Autowired
    private QnATypeDaoImpl qnATypeDao;

    @Override
    public int execute(QnAInsertCommand command, UserVO user) {
        QnATypeVO qnATypeVO = null;
        try {
            qnATypeVO = qnATypeDao.selectById(command.getType());
        } catch (QnATypeSelectException e) {
            e.printStackTrace();
        }

        QnAVO qna = new QnAVO();
        qna.setUser(user);
        qna.setTitle(command.getTitle());
        qna.setType(qnATypeVO);
        qna.setContent(command.getContent());

        try {
            return qnADao.insert(qna);
        } catch (QnAException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
