package kr.co.dinner41.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.dinner41.exception.menu.MenuDeleteFailedException;
import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.exception.menu.OfferTypeSelectException;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.OfferTypeVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class MenuDaoImplTester {
	@Autowired
	private ApplicationContext ctx;	
	
	@Ignore
	@Test
	public void insertTest() throws OfferTypeSelectException, SQLException {
		MenuDaoImpl menuDao=ctx.getBean("menuDao",MenuDaoImpl.class);
		OfferTypeDaoImpl offerTypeDao=ctx.getBean("offerTypeDao",OfferTypeDaoImpl.class);
		OfferTypeVO offerTypeVo=offerTypeDao.selectById("PAC");
		StoreDaoImpl storeDao = ctx.getBean("storeDao",StoreDaoImpl.class);
		StoreVO store = storeDao.selectByUserId(4);
		int menuId = menuDao.getLastInsertId(store.getId());
		
		MenuVO menu = new MenuVO(store,menuId,offerTypeVo,"도시락","불고기 도시락",4500,5,"불고기 도시락입니다.","당일 섭취가 원칙입니다.","photo.jpg",null);
		
	
			try {
				menuDao.insert(menu, store);
			} catch (MenuException e) {
				e.printStackTrace();
			}
		
System.out.println("메뉴 추가 성공");
	}

	@Ignore
	@Test
    public void testSelecByStoreId(){
        MenuDao dao = ctx.getBean("menuDao", MenuDaoImpl.class);
        try {
            List<MenuVO> list = dao.selectByStoreId(1,1,10);
            System.out.println("Success");
            for (MenuVO vo: list) {
                System.out.println(vo);
            }
        } catch (MenuException e) {
            e.printStackTrace();
        }
    }
	
	
	@Ignore
	@Test
    public void testUserSelecByStoreId(){
        MenuDao dao = ctx.getBean("menuDao", MenuDaoImpl.class);
        try {
            List<MenuVO> list = dao.userSelectByStoreId(1,1,10);
            System.out.println("Success");
            for (MenuVO vo: list) {
                System.out.println(vo);
            }
        } catch (MenuException e) {
            e.printStackTrace();
        }
    }
	

    @Test
    public void testUpdate() throws OfferTypeSelectException, SQLException {
    	MenuDaoImpl menuDao=ctx.getBean("menuDao",MenuDaoImpl.class);
        OfferTypeDaoImpl offerTypeDao = ctx.getBean("offerTypeDao", OfferTypeDaoImpl.class);
        OfferTypeVO offerTypeVo = offerTypeDao.selectById("PAC");
        
        MenuVO me = null;
        StoreDaoImpl storeDao = ctx.getBean("storeDao",StoreDaoImpl.class);
		StoreVO store = storeDao.selectById(1);
		int menuId = me.getId();
		String photo = me.getPhoto();
		MenuVO menu = new MenuVO(store,menuId,offerTypeVo,null,"제육 도시락",600,5,"제육도시락입니다.","당일 섭취가 원칙입니다.",photo,null);
		

        try {
            menuDao.update(menu,store);
        
        } catch (MenuException e) {
            e.printStackTrace();
        }
    }



	@Ignore
	@Test
	public void deleteTest() throws MenuException {
		MenuDao dao=ctx.getBean("menuDao",MenuDao.class);
		try {
			dao.delete(2,1);
			System.out.println("메뉴 삭제하기에 성공했습니다.");
		}
		catch(MenuDeleteFailedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	

	@Ignore
	@Test
	public void selectTest() {
		UserDao userDao=ctx.getBean("userDao",UserDao.class);
		List<UserVO> users=null;
		try {
			users=userDao.selectAll();
			if(users==null) {
				System.out.println("회원의 목록을 불러오지 못함");
			}
			for(UserVO user:users) {
				System.out.println(user.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
