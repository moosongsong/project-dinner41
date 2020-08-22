package kr.co.dinner41.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.exception.login.UserNotFoundException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.vo.UserVO;

@Service("checkEmailService")
public class CheckEmailServiceImpl implements CheckEmailService {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Override
	public UserVO execute(String email) throws UserException {
		System.out.println("시스템 진입");
		UserVO user=userDao.selectByEmail(email);
		if(user==null) {
			throw new UserNotFoundException("해당하는 이메일을 가지는 회원이 존재하지 않습니다.");
		}
		System.out.println(user.getEmail());
		
		return user;
	}

}
