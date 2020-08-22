package kr.co.dinner41.service.login;

import javax.servlet.http.HttpSession;

import kr.co.dinner41.exception.user.UserException;

public interface LogoutService {
	void execute(HttpSession session)throws UserException;

}
