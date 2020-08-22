package kr.co.dinner41.service.qna;

import kr.co.dinner41.command.QnAAnswerCommand;
import kr.co.dinner41.dao.QnADaoImpl;
import kr.co.dinner41.exception.QnAException;
import kr.co.dinner41.vo.QnAVO;
import kr.co.dinner41.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qnAAnswerService")
public class QnAAnswerServiceImpl implements QnAAnswerService{
    @Autowired
    private QnADaoImpl qnADao;

    @Override
    public void execute(UserVO manager, int qnaId, QnAAnswerCommand command) {
        QnAVO qna=new QnAVO();
        qna.setManager(manager);
        qna.setId(qnaId);
        qna.setAnswerContent(command.getContent());
        try {
            qnADao.update(qna);
        } catch (QnAException e) {
            e.printStackTrace();
        }
    }
}
