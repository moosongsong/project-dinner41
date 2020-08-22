package kr.co.dinner41.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.dinner41.command.UserInsertCommand;
import kr.co.dinner41.command.UserUpdateCommand;
import kr.co.dinner41.exception.login.UserNotFoundException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.service.user.CheckEmailService;
import kr.co.dinner41.service.user.CheckPasswordService;
import kr.co.dinner41.service.user.UserDeleteService;
import kr.co.dinner41.service.user.UserInsertService;
import kr.co.dinner41.service.user.UserUpdateService;
import kr.co.dinner41.service.user.UserViewService;
import kr.co.dinner41.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userInsertService")
	private UserInsertService insertService;

	@Autowired
	@Qualifier("userViewService")
	private UserViewService viewService;

	@Autowired
	@Qualifier("userDeleteService")
	private UserDeleteService deleteService;

	@Autowired
	@Qualifier("userUpdateService")
	private UserUpdateService updateService;
	
	@Autowired
	@Qualifier("checkPasswordService")
	private CheckPasswordService checkPasswordService;
	
	@Autowired
	@Qualifier("checkEmailService")
	private CheckEmailService checkEmailService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String insert() {
		return "common/register";
	}
	
	@ResponseBody
	@RequestMapping(value="/register/checkEmail",method=RequestMethod.POST)
	public HashMap<String,Object> checkEmail(HttpServletRequest request){
		System.out.println("컨트롤러 진입");
		String email=request.getParameter("email");
		System.out.println(email);
		HashMap<String,Object> result=new HashMap<>();
		UserVO user=null;
		try {
			user=checkEmailService.execute(email);

			System.out.println(user);
			result.put("result", true);
			result.put("user", user);
		}
		catch(UserNotFoundException e) {
			result.put("result", false);
		}
		return result;
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String insert(UserInsertCommand command, Errors errors, HttpServletRequest request) {
		/*
		new UserInsertValidator().validate(command,errors);
		if(errors.hasErrors()) {
			return "common/register";
		}
		*/
		System.out.println("insertController진입");
		System.out.println(command.getEmail());
		System.out.println(command.getPassword());
		System.out.println(command.getPasswordConfirm());
		System.out.println(command.getName());
		System.out.println(command.getAddress());
		System.out.println(command.getSubAddress());
		System.out.println(command.getLatitude());
		System.out.println(command.getLongitude());
		System.out.println(command.getPhone1());
		System.out.println(command.getPhone2());
		System.out.println(command.getPhone3());
		System.out.println(command.getType());
		
		try {
			UserVO user=insertService.execute(command);
			String userType=user.getType().getId();
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", user);
			switch(userType) {
			case "GM":
				return "user/userHome";
			case "AD":
				return "manage/managerHome";
			case "SM":
				return "store/storeHome";
			}
		}catch(UserException e) {
			
		}
		return "common/register";
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/checkPass",method=RequestMethod.POST)
	public HashMap<String, Object> checkPassword(HttpServletRequest request) {
		System.out.println("checkPassword진입");
		HashMap<String, Object> map = new HashMap<>();
		try {
			checkPasswordService.execute(request);
			map.put("result", true);
		}
		catch(UserException e) {
			map.put("result", false);
			
		}
		return map;
	}
	
	@RequestMapping(value="/mypage/update",method=RequestMethod.GET)
	public String returnInsertForm() {
		return "user/myPageInsert";
	}

	
	@RequestMapping(value="/mypage",method=RequestMethod.POST)
	public String update(UserUpdateCommand command,Model model,HttpSession session) {
		System.out.println("업데이트 수정 핸들러 들어옴");
		try {
			UserVO updatedUser=updateService.execute(command, session);
			session.removeAttribute("loginUser");
			session.setAttribute("loginUser", updatedUser);
			return "redirect:/mypage";
		}
		catch(UserException e) {
			return "user/myPageInsert";
			
		}
	}
	
	@RequestMapping(value="/mypage",method=RequestMethod.GET)
	public String view(HttpSession session,Model model) {
		UserVO user=null;
		try {
			user=viewService.execute(session);
			model.addAttribute("viewTargetUser", user);
		}catch(UserException e) {
			return "common/error";
		}
		return "user/myPageView";
	}
	
	@RequestMapping(value="/mypage/delete",method=RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		System.out.println("delete 이벤트 핸들러 진입");
		try {
			deleteService.execute(request);
			mav.setViewName("common/login");
		}
		catch(UserException e) {
			System.out.println(e.getMessage());
			mav.addObject("errorMessage", e.getMessage());
			mav.setViewName("user/myPageView");
		}
		return mav;
	}

}
