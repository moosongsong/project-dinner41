package kr.co.dinner41.service.menu;

import java.util.List;

import kr.co.dinner41.vo.MenuVO;

public interface MenuListByUserService {

	public List<MenuVO> execute(List<MenuVO> menus);
	
}
