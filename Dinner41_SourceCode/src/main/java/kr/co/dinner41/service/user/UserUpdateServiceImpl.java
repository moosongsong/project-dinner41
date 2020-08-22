package kr.co.dinner41.service.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.command.UserUpdateCommand;
import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.exception.user.NoSuchUserException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.exception.user.WrongPasswordConfirmException;
import kr.co.dinner41.exception.user.WrongPasswordException;
import kr.co.dinner41.vo.UserVO;

@Service("userUpdateService")
public class UserUpdateServiceImpl implements UserUpdateService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Override
	public UserVO execute(UserUpdateCommand command, HttpSession session) throws UserException,NumberFormatException {
		String name=command.getName();
		String phone=command.getPhone1()+command.getPhone2()+command.getPhone3();
		String address=command.getAddress();
		String subAddress=command.getSubAddress();
		String strLatitude=command.getLatitude();
		String strLongitude=command.getLongitude();
		String newPassword=command.getNewPassword();
		String newPasswordConfirm=command.getNewPasswordConfirm();
		
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		int loginUserId=loginUser.getId();
		
		UserVO targetUser=userDao.selectById(loginUserId); //NoSuchUserException
		if(targetUser==null) {
			throw new NoSuchUserException();
		}
		
		targetUser.setName(name);
		targetUser.setPhone(phone);
		targetUser.setAddress(address);
		targetUser.setSubAddress(subAddress);
		double latitude=Double.parseDouble(strLatitude);
		double longitude=Double.parseDouble(strLongitude);
		targetUser.setLatitude(latitude);
		targetUser.setLongitude(longitude);

		if(newPassword!=null) {
			if(!newPassword.equals(newPasswordConfirm)) {
				throw new WrongPasswordConfirmException();
			}
			targetUser.setPassword(newPassword);
		}
		
		UserVO resultUser=userDao.update(targetUser);

		return resultUser;
	}

}
