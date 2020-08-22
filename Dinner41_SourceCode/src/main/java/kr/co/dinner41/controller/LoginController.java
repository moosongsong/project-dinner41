package kr.co.dinner41.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.dinner41.command.LoginCommand;
import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.login.QuitUserException;
import kr.co.dinner41.exception.login.SendEmailFailedException;
import kr.co.dinner41.exception.login.UserNotFoundException;
import kr.co.dinner41.exception.user.UserUpdateFailedException;
import kr.co.dinner41.service.login.LoginService;
import kr.co.dinner41.service.login.LogoutService;
import kr.co.dinner41.service.login.SearchUserByEmailService;
import kr.co.dinner41.service.login.SendTempPasswordService;
import kr.co.dinner41.validator.LoginValidator;
import kr.co.dinner41.vo.CartVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

@Controller
public class LoginController {
	@Autowired
	@Qualifier("loginService")
	private LoginService loginService;

	@Autowired
	@Qualifier("searchUserByEmailService")
	private SearchUserByEmailService searchUserByEmailService;
	
	@Autowired
	@Qualifier("sendTempPasswordService")
	private SendTempPasswordService sendTempPasswordService;
	
	@Autowired
	@Qualifier("logoutService")
	private LogoutService logoutService;
	
	@Autowired
	@Qualifier("storeDao")
	StoreDao storeDao;
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		logoutService.execute(session);
		return "common/login";
	}
	
	
	private String getUserPage(UserVO loginUser,Model model) {
		if(loginUser==null) {
			return null;
		}
		String userType=loginUser.getType().getId();
		switch(userType) {
		case "GM":
			return "user/userHome";
		case "SM":
			int userId = loginUser.getId();
			StoreVO store = storeDao.selectByUserId(userId);
			model.addAttribute("store",store);
			return "store/storeHome";
		case "AD":
			return "manage/managerHome";
		default:
			return null;
		}
	}

	@RequestMapping(value="/",method=RequestMethod.GET)

	public String login(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession(false);
		if(session==null) {
			return "common/login";
		}
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		if(loginUser==null) {
			return "common/login";
		}
		String result=getUserPage(loginUser,model);
		return result;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(LoginCommand command,Errors errors,HttpServletRequest request,Model model) {

		ModelAndView mav=new ModelAndView();
		new LoginValidator().validate(command, errors);
		if(errors.hasErrors()) {
			mav.setViewName("common/login");
			return mav;
		}
		
		HttpSession session=request.getSession();
		
		try {
			loginService.execute(command, session);
			mav.addObject("result", "로그인 성공!");
		}catch(UserNotFoundException|QuitUserException e) {
			mav.addObject("loginErrorMessage",e.getMessage());
			mav.setViewName("common/login");
			return mav;
		}

		UserVO user=(UserVO)session.getAttribute("loginUser");
		String viewName=null;
		viewName=getUserPage(user,model);
		if(viewName.equals("user/userHome")) {
			session.setAttribute("carts", new ArrayList<CartVO>());
		}
		mav.setViewName(viewName);
		return mav;
	}
	
	
	
	@RequestMapping(value="/sessionCheck",method=RequestMethod.GET)
	public String sessionCheck(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if(session==null) {
			return "common/login";
		}
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		if(loginUser==null) {
			return "common/login";
		}
		String loginUserType=loginUser.getType().getId();
		String jspName=null;
		switch(loginUserType) {
		case "GM":
			jspName="user/userHome";
			break;
		case "SM":
			jspName="store/storeHome";
			break;
		case "AD":
			jspName="manage/managerHome";
		}
		return jspName;
	}
	
	@RequestMapping(value="/password",method=RequestMethod.GET)
	public String searchPassword() {
		return "common/searchPassword";
	}
	
	@RequestMapping(value="/password",method=RequestMethod.POST)
	public ModelAndView searchPassword(@RequestParam("email") String email) {
		System.out.println("(searchPassword event handler) email: "+email);
		ModelAndView mav=new ModelAndView();
		try {

			UserVO user=searchUserByEmailService.exectue(email);
			if(user==null) {
				mav.addObject("userSearchResult", "해당 하는 이메일을 가지는 회원이 존재하지 않습니다.이메일을 확인해주세요");
				mav.setViewName("common/searchPassword");
			}
			sendTempPasswordService.execute(user);
			mav.addObject("resultUser", user);
			//mav.addObject("resultEmail", user.getEmail());
			//mav.addObject("resultMessage",user.getEmail()+"님에게 임시비밀번호를 발급했습니다. 이메일을 확인해주세요");
			mav.setViewName("common/login");
			return mav;
		}catch(UserNotFoundException e) {
			mav.setViewName("common/searchPassword");
			mav.addObject("errorCode", "1");
			mav.addObject("errorMessage", "해당 하는 이메일을 가지는 회원이 존재하지 않습니다.이메일을 확인해주세요");

		}
		catch(UserUpdateFailedException e) {
			mav.setViewName("common/searchPassword");
			mav.addObject("errorCode", "2");
			mav.addObject("defaultEmail",email);
			mav.addObject("errorMessage", "해당 회원의 비밀번호를 임시비밀번호로 변경하는데 실패했습니다.임시비밀번호 발급을 다시 해주세요");
		}
		catch(SendEmailFailedException e) {
			mav.setViewName("common/searchPassword");
			mav.addObject("errorCode", "2");
			mav.addObject("errorMessage","임시비밀번호 발급 이메일을 전송하지 못했습니다.");
		}
		return mav;
	}
	
}
