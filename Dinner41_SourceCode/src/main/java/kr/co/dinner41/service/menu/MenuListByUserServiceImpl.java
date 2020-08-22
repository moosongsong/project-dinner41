package kr.co.dinner41.service.menu;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.dinner41.vo.MenuVO;

@Service("menuListByUserService")
public class MenuListByUserServiceImpl implements MenuListByUserService {
	
	public static final String CONDITION_TITLE = "menu_name";
	public static final String CONDITION_DESCRIPTION = "menu_description";
	public static final String CONDITION_TAG = "menu_tag";
	public static final int PAGESIZE = 10;
	
	@Override
	public List<MenuVO> execute(List<MenuVO> menus) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
