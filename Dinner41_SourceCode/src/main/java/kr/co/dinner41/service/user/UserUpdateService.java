package kr.co.dinner41.service.user;

import javax.servlet.http.HttpSession;

import kr.co.dinner41.command.UserUpdateCommand;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.vo.UserVO;

public interface UserUpdateService {
	UserVO execute(UserUpdateCommand command,HttpSession session)throws UserException,NumberFormatException;
}
