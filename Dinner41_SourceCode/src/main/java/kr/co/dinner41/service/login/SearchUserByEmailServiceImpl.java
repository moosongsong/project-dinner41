package kr.co.dinner41.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.exception.login.SearchPasswordException;
import kr.co.dinner41.exception.login.UserNotFoundException;
import kr.co.dinner41.vo.UserVO;

@Service("searchUserByEmailService")
public class SearchUserByEmailServiceImpl implements SearchUserByEmailService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Override
	public UserVO exectue(String email) throws SearchPasswordException {
		UserVO user=userDao.selectByEmail(email);
		if(user==null) {
			throw new UserNotFoundException("해당 이메일을 아이디로 가진 회원이 존재하지 않습니다.");
		}
		return user;
	}

}
