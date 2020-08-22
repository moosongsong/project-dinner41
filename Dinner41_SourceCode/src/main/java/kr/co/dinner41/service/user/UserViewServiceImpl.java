package kr.co.dinner41.service.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.exception.user.NoSuchUserException;
import kr.co.dinner41.exception.user.NotLoginStateException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.vo.UserVO;

@Service("userViewService")
public class UserViewServiceImpl implements UserViewService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Override
	public UserVO execute(HttpSession session) throws UserException {
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		UserVO resultUser=null;
		if(loginUser==null) {
			throw new NotLoginStateException();
		}
		int loginUserId=loginUser.getId();
		try {
			loginUser=userDao.selectById(loginUserId);
		}catch(UserException e) {
			throw new NoSuchUserException();
		}
		return loginUser;
	}

}
