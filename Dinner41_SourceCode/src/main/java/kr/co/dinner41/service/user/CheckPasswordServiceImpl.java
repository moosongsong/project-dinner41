package kr.co.dinner41.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.exception.user.NoSuchUserException;
import kr.co.dinner41.exception.user.NotLoginStateException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.vo.UserVO;

@Service("checkPasswordService")
public class CheckPasswordServiceImpl implements CheckPasswordService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Override
	public void execute(HttpServletRequest request) throws UserException {
		String password=(String)request.getParameter("password");
		HttpSession session=request.getSession(false);
		if(session==null) {
			throw new NotLoginStateException();
		}
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			throw new NotLoginStateException();
		}
		String email=user.getEmail();
		UserVO result=userDao.selectByEmailAndPassword(email, password);
		if(result==null) {
			throw new NoSuchUserException();
		}
	}

}
