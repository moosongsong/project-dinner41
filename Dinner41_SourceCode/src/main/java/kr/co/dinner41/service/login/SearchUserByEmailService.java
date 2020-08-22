package kr.co.dinner41.service.login;

import kr.co.dinner41.exception.login.SearchPasswordException;
import kr.co.dinner41.vo.UserVO;

public interface SearchUserByEmailService {
	UserVO exectue(String email)throws SearchPasswordException;
}
