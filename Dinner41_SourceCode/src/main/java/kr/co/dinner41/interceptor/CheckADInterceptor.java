package kr.co.dinner41.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.dinner41.vo.UserVO;

public class CheckADInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserVO loginUser=(UserVO)request.getSession(false).getAttribute("loginUser");
		String userType=loginUser.getType().getId();
		if(userType.equals("AD"))
			return true;
		return false;
	}
}
