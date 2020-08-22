package kr.co.dinner41.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.OpenState;
import kr.co.dinner41.vo.StoreCategoryVO;
import kr.co.dinner41.vo.StoreListByUserViewVO;
import kr.co.dinner41.vo.StoreStateVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserTypeVO;
import kr.co.dinner41.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
	})
public class StoreDaoImplTester {
	@Autowired
	private ApplicationContext ctx;
	

//	@Test
//	public void insertTest() {
//		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
//		UserDao userDao =ctx.getBean("userDao",UserDao.class);
//		UserTypeDao userTypeDao = ctx.getBean("userTypeDao",UserTypeDao.class);
//		StoreCategoryDao storeCategoryDao =ctx.getBean("storeCategoryDao",StoreCategoryDao.class);
//		StoreStateDao storeStateDao = ctx.getBean("storeStateDao",StoreStateDao.class);
//		
//		UserTypeVO userType = new UserTypeVO("SM","점주회원");
//		//UserVO user =new UserVO(0,userType,"hong3@naver.com","1234","홍길동","서울시 관악구 행운5길 23-4","우진하우스 b01호",37.481978,126.958761,"010-4416-9941",
//		//				null,null);
//		//userDao.insert(user);
//		UserVO user = userDao.selectById(17);
//		OpenState openState = OpenState.OPEN;
//		StoreCategoryVO storeCategory = new StoreCategoryVO("KOR","한식");
//		StoreStateVO storeState = new StoreStateVO(2,"승인");
//		StoreVO store = new StoreVO(0,user,storeCategory,storeState,"1231231231","자연별곡","서울시 관악구 중앙동 관악로 195","6층 자연별곡",37.482417,126.953073,"010-2222-3333",
//									"2시-22시","kor.jpg","자연별곡입니다",null,"12345644");
//		try {
//			storeDao.insert(store);
//			System.out.println("매장등록성공");
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}

	@Ignore
	@Test
	public void insertTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		UserDao userDao =ctx.getBean("userDao",UserDao.class);
		UserTypeDao userTypeDao = ctx.getBean("userTypeDao",UserTypeDao.class);
		StoreCategoryDao storeCategoryDao =ctx.getBean("storeCategoryDao",StoreCategoryDao.class);
		StoreStateDao storeStateDao = ctx.getBean("storeStateDao",StoreStateDao.class);
		
	}
	
	@Test
	public void updateTest() {
		StoreDao storeDao = ctx.getBean("storeDao",StoreDao.class);
		int result = 0;
		UserTypeVO userType = new UserTypeVO("sm","점주회원");
		UserVO user = new UserVO();
		user.setId(14);
		StoreCategoryVO storeCategory = new StoreCategoryVO();
		storeCategory.setId("MEA");
		StoreStateVO storeState = new StoreStateVO();
		storeState.setId(2);
		OpenState openState = OpenState.OPEN;
		
		StoreVO store = new StoreVO(7,user,storeCategory,storeState,"123456784","안녕베트남1","서울특별시 관악구 봉천동 관악로14길 70","안녕베트남(지하)",37.478386,126.956271,
				"02-877-3875","연중 무휴","a.jpg","안녕베트남, 설대입구점이오",openState,"12341234");
		try {
			storeDao.update(store);
			System.out.println(store.getIntroduction());
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	

	
	

	@Ignore
	@Test
	public void selectByIdTest() {
		StoreDao storeDao = ctx.getBean("storeDao",StoreDao.class);
		StoreVO store =null;
		
		try {
			store=storeDao.selectById(1);
			if(store==null) {
				System.out.println("매장 못 찾음");
			}
			System.out.println(store.getName());
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void selectByCategoryTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		List<StoreVO> stores = null;
		try {
			stores = storeDao.selectByCategoryName("밀키트",37.482566,126.953100,1, 2);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreVO store:stores) {
				System.out.println(store.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void selectByCategoryOnMapTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		List<StoreVO> stores = null;
		try {
			stores = storeDao.selectByCategoryNameOnMap("밀키트",37.482566,126.953100);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreVO store:stores) {
				System.out.println(store.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void selectByStateNameTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		List<StoreVO> stores = null;
		try {
			stores = storeDao.selectByStateName("승인",1, 10);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreVO store:stores) {
				System.out.println(store.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void selectByStateNameAndNameTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		List<StoreVO> stores = null;
		try {
			stores = storeDao.selectByStateNameAndName("신청","%",1, 10);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreVO store:stores) {
				System.out.println(store.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void selectByBusinessTest() {
		StoreDao storeDao = ctx.getBean("storeDao",StoreDao.class);
		StoreVO store =null;
		
		try {
			store=storeDao.selectByBusinessNumber("12345678");
			if(store==null) {
				System.out.println("매장 못 찾음");
			}
			System.out.println(store.getName());
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	@Ignore
	@Test
	public void selectByStoreNameTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		List<StoreVO> stores = null;
		try {
			stores = storeDao.selectByName("애슐리 퀸즈",37.482566,126.953100, 1, 2);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreVO store:stores) {
				System.out.println(store.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void selectByLocationTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		List<StoreVO> stores = null;
		try {
			stores = storeDao.selectByLocation(37.482566,126.953100);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreVO store:stores) {
				System.out.println(store.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void selectByOpenStateTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		List<StoreVO> stores = null;
		try {
			stores = storeDao.selectByOpenState(OpenState.OPEN, 1, 10);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreVO store:stores) {
				System.out.println(store.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	@Ignore
	@Test
	public void selectAllTest() {
		StoreDao storeDao =ctx.getBean("storeDao",StoreDao.class);
		List<StoreVO> stores = null;
		try {
			stores = storeDao.selectAll(1, 10);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreVO store:stores) {
				System.out.println(store.getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void  selectViewByCategoryNameTest() {
		StoreDao storeDao = ctx.getBean("storeDao",StoreDao.class);
		List<StoreListByUserViewVO> stores =null;
		try {
			stores = storeDao.selectViewByCategoryName("%", 37.481978,126.958761,1,10);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreListByUserViewVO store:stores) {
				System.out.println(store.getStoreName());
				System.out.println(store.getReviewScore());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void  selectViewByStoreNameOrMenuNameTest() {
		StoreDao storeDao = ctx.getBean("storeDao",StoreDao.class);
		List<StoreListByUserViewVO> stores =null;
		try {
			stores = storeDao.selectViewByStoreNameOrMenuName("%", 37.481978,126.958761,1,5);
			if(stores==null) {
				System.out.println("매장들을 불러오지 못함");
			}
			for(StoreListByUserViewVO store:stores) {
				System.out.println(store.getStoreName());
				System.out.println(store.getReviewScore());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
