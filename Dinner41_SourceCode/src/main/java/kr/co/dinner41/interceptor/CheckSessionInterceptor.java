package kr.co.dinner41.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.dinner41.vo.UserVO;

public class CheckSessionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null) {
			return false;
		}

		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			return false;
		}
		return true;
	}
}
