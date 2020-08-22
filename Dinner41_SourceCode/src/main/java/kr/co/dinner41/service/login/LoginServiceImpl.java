package kr.co.dinner41.service.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dinner41.command.LoginCommand;
import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.exception.login.LoginException;
import kr.co.dinner41.exception.login.QuitUserException;
import kr.co.dinner41.exception.login.UserNotFoundException;
import kr.co.dinner41.exception.user.UserSelectFailedException;
import kr.co.dinner41.vo.UserVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserDao userDao; 
	@Override
	public void execute(LoginCommand command, HttpSession session) throws LoginException {
		
		String email=command.getEmail();
		String password=command.getPassword();
		
		UserVO user=userDao.selectByEmailAndPassword(email, password);
		if(user==null) {
			throw new UserNotFoundException("해당 이메일과 비밀번호를 가지는 회원이 존재하지 않습니다");
		}
		if(user.getRemoveDate()!=null) {
			throw new QuitUserException();
		}
		session.setAttribute("loginUser",user);
	}
}
