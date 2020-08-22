package kr.co.dinner41.service.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.MenuDaoImpl;
import kr.co.dinner41.dao.StoreDaoImpl;
import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

@Service("menuListByStoreService")
public class MenuListByStoreServiceImpl implements MenuListByStoreService {

	@Autowired
	private StoreDaoImpl storeDao;

	@Autowired
	private MenuDaoImpl menuDao;
	public static final int PAGE_SIZE = 5;
	public static final int NUMBER_OF_PAGE_IN_ONE_PAGE = 4;

	@Override
	public List<MenuVO> execute(int storeId, int page) throws StoreException, MenuException {
		List<MenuVO> menus = null;
		int totalRecord = 0;

		try {
			totalRecord = menuDao.getTotalRecord(storeId);
		} catch (MenuException e) {
			e.printStackTrace();
		}

		int totalPage = totalRecord / PAGE_SIZE + 1;
		if (page > totalPage) {
			page = totalPage;
		}

		if (page < 1) {
			page = 1;
		}
		
		try {
		menus = menuDao.selectByStoreId(storeId, page, PAGE_SIZE);
		}
		catch (MenuException e) {
			e.printStackTrace();
		}

		return menus;
	}

	@Override
	public List<PageVO> getPages(int nowPage, int storeId) {
		List<PageVO> list = new ArrayList<>();
		
		int totalRecord = 0;
		
		try {
		totalRecord = menuDao.getTotalRecord(storeId);
		}
		catch (MenuException e) {
			e.printStackTrace();
		}
		
		int totalPage = totalRecord / PAGE_SIZE;
		if((totalRecord%PAGE_SIZE) != 0) {
			totalPage = totalPage + 1;
		}
		
		if (nowPage > totalPage) {
			nowPage = totalPage;
		}
		
		if(nowPage < 1) {
			nowPage = 1;
		}
		
		 int startPoint = nowPage/ NUMBER_OF_PAGE_IN_ONE_PAGE;
	        int startPage = startPoint* NUMBER_OF_PAGE_IN_ONE_PAGE + 1;

	        int endPage = startPage + NUMBER_OF_PAGE_IN_ONE_PAGE - 1;
	        if (endPage > totalPage){
	            endPage = totalPage;
	        }

	        int first=0;
	        if (startPage>1){
	            first = startPage-1;
	        }
	        if (startPage<=1){
	            first = 1;
	        }
	        PageVO firstPage = new PageVO("<<", first);

	        int last = 0;
	        if (endPage<totalPage){
	            last = endPage +1;
	        }
	        if (endPage >= totalPage){
	            last = endPage;
	        }
	        PageVO lastPage = new PageVO(">>", last);

	        list.add(firstPage);
	        for (int i=startPage; i<=endPage; i++){
	            PageVO pageVO = new PageVO(""+i, i);
	            list.add(pageVO);
	        }
	        list.add(lastPage);

	        return list;
	    }

}
