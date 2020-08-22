package kr.co.dinner41.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.exception.user.NoSuchUserException;
import kr.co.dinner41.exception.user.NotLoginStateException;
import kr.co.dinner41.exception.user.UserDeleteFailedException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.exception.user.WrongPasswordException;
import kr.co.dinner41.vo.UserVO;

@Service("userDeleteService")
public class UserDeleteServiceImpl implements UserDeleteService {
	@Autowired
	@Qualifier("userDao")
	private UserDao dao;

	@Override
	public void execute(HttpServletRequest request) throws UserException {
		//String passwordConfirm=(String)request.getAttribute("passwordConfirm");
		//System.out.println(passwordConfirm);
		HttpSession session=request.getSession(false);
		if(session==null) {
			throw new NotLoginStateException();
		}
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			throw new NotLoginStateException();
		}
		int userId=user.getId();
		
		UserVO result=dao.selectById(userId);
		if(result==null) {
			throw new NoSuchUserException();
		}
		
		try {
			dao.delete(userId);
		}
		catch(UserException e) {
			throw new UserDeleteFailedException();
		}
	}

}
