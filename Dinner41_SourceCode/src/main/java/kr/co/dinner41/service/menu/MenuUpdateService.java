package kr.co.dinner41.service.menu;

import javax.servlet.http.HttpSession;

import kr.co.dinner41.command.MenuUpdateCommand;
import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.vo.UserVO;

public interface MenuUpdateService {
	
	void execute(MenuUpdateCommand command, int storeId, int menuId,  UserVO user,HttpSession session) throws MenuException;

}
