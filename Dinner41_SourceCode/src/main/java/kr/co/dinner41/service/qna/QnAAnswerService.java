package kr.co.dinner41.service.qna;

import kr.co.dinner41.command.QnAAnswerCommand;
import kr.co.dinner41.vo.UserVO;

public interface QnAAnswerService {
    void execute(UserVO manager, int qnaId, QnAAnswerCommand command);
}
