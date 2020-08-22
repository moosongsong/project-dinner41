package kr.co.dinner41.service.user;

import javax.servlet.http.HttpServletRequest;

import kr.co.dinner41.exception.user.UserException;

public interface CheckPasswordService {
	void execute(HttpServletRequest request)throws UserException;

}
