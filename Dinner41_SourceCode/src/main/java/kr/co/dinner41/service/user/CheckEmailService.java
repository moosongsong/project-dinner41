package kr.co.dinner41.service.user;

import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.vo.UserVO;

public interface CheckEmailService {
	UserVO execute(String email) throws UserException;
}
