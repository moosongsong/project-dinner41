package kr.co.dinner41.service.login;

import kr.co.dinner41.exception.login.LoginException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.vo.UserVO;

public interface SendTempPasswordService {
	void execute(UserVO user)throws LoginException,UserException;
}
