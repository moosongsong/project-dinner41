package kr.co.dinner41.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.command.UserInsertCommand;
import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.dao.UserTypeDao;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.exception.usertype.UserTypeException;
import kr.co.dinner41.vo.UserTypeVO;
import kr.co.dinner41.vo.UserVO;


@Service
@Qualifier("userInsertService")
public class UserInsertServiceImpl implements UserInsertService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("userTypeDao")
	private UserTypeDao userTypeDao;


	@Override
	public UserVO execute(UserInsertCommand command) throws UserException {
		String type=command.getType();
		String email=command.getEmail();
		String password=command.getPassword();
		String name=command.getName();
		String address=command.getAddress();
		String subAddress=command.getSubAddress();
		String strLatitude=command.getLatitude();
		String strLongitude=command.getLongitude();
		String phone=command.getPhone1()+command.getPhone2()+command.getPhone3();
		UserTypeVO userType=null;
		UserVO resultUser=null;
		double latitude=0;
		double longitude=0;
		

		try {
			userType=userTypeDao.selectById(type);
			latitude=Double.parseDouble(strLatitude);
			longitude=Double.parseDouble(strLongitude);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			throw new UserException("입력받은 위도 경도 값이 올바르지 않습니다.");
		}
		catch(UserTypeException e) {
			throw new UserException(e.getMessage());
		}
		
		UserVO user=new UserVO(0,userType,email,password,name,address,subAddress,latitude,longitude,phone,null,null);

		resultUser=userDao.insert(user);
		return resultUser;
	}

}

