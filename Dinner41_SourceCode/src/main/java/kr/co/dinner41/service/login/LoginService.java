package kr.co.dinner41.service.login;

import javax.servlet.http.HttpSession;

import kr.co.dinner41.command.LoginCommand;
import kr.co.dinner41.exception.login.LoginException;

public interface LoginService {
	void execute(LoginCommand command,HttpSession session)throws LoginException;
}
