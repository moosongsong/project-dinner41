package kr.co.dinner41.service.user;

import javax.servlet.http.HttpSession;

import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.vo.UserVO;

public interface UserViewService {
	UserVO execute(HttpSession session)throws UserException;
}
