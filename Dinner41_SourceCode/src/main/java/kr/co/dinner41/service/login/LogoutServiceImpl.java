package kr.co.dinner41.service.login;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import kr.co.dinner41.exception.user.NotLoginStateException;
import kr.co.dinner41.exception.user.UserException;

@Service("logoutService")
public class LogoutServiceImpl implements LogoutService {

	@Override
	public void execute(HttpSession session) throws UserException {
		if(session==null) {
			throw new NotLoginStateException();
		}
		//session.removeAttribute("loginUser");
		session.invalidate();
	}

}
