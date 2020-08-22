package kr.co.dinner41.service.user;

import kr.co.dinner41.command.UserInsertCommand;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.vo.UserVO;

public interface UserInsertService {
	UserVO execute(UserInsertCommand command)throws UserException;
}
