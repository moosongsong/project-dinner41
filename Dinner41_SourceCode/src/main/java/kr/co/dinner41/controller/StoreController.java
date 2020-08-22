package kr.co.dinner41.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.dinner41.command.StoreInsertCommand;
import kr.co.dinner41.command.StoreUpdateCommand;
import kr.co.dinner41.dao.StoreCategoryDao;
import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.service.review.ReviewListService;
import kr.co.dinner41.service.store.StoreDeleteService;
import kr.co.dinner41.service.store.StoreInsertService;
import kr.co.dinner41.service.store.StoreListByManagerService;
import kr.co.dinner41.service.store.StoreListByUserService;
import kr.co.dinner41.service.store.StoreOpenStateService;
import kr.co.dinner41.service.store.StoreUpdateService;
import kr.co.dinner41.service.store.StoreViewByStoreService;
import kr.co.dinner41.service.store.StoreViewByUserService;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.OpenState;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.ReviewVO;
import kr.co.dinner41.vo.StoreCategoryVO;
import kr.co.dinner41.vo.StoreListByUserViewVO;
import kr.co.dinner41.vo.StoreStateVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;


@Controller
@RequestMapping()
public class StoreController {
	@Autowired
	@Qualifier("storeInsertService")
	StoreInsertService storeInsertService;
	
	@Autowired
	@Qualifier("storeDeleteService")
	StoreDeleteService storeDeleteService;
	
	@Autowired
	@Qualifier("storeUpdateService")
	StoreUpdateService storeUpdateService;
	
	@Autowired
	@Qualifier("storeViewByStoreService")
	StoreViewByStoreService storeViewByStoreService;
	
	@Autowired
	@Qualifier("storeListByUserService")
	StoreListByUserService storeListByUserService;
	
	@Autowired
	@Qualifier("storeListByManagerService")
	StoreListByManagerService storeListByManagerService;

	@Autowired
	@Qualifier("storeViewByUserService")
	StoreViewByUserService storeViewByUserService;

	@Autowired
	@Qualifier("reviewListService")
	ReviewListService reviewListService;
	
	@Autowired
	@Qualifier("storeOpenStateService")
	StoreOpenStateService storeOpenStateService;
	
	@Autowired
	@Qualifier("storeCategoryDao")
	StoreCategoryDao storeCategoryDao;
	
	@Autowired
	@Qualifier("storeDao")
	StoreDao storeDao;
	
	
	
	
	@RequestMapping(value="/sm/store",method=RequestMethod.GET)
	public String insert(HttpSession session,Model model) {
		UserVO user = (UserVO)session.getAttribute("loginUser");
		int userId = user.getId();
		StoreVO store = null;
		store = storeViewByStoreService.execute(userId);
		
		if(store==null) {
			model.addAttribute("store",store);
	
			return "store/storeRegister";
		}
		
		else if(store.getState().getName().equals("점주삭제")) {
			model.addAttribute("store",store);
			return "store/storeRegister"; //처리를 새롭게 해야할 듯 
		}
		else if(store.getState().getName().equals("관리자정지")) {
			
		}
		else if(store.getState().getName().equals("관리자삭제")) {
			
		}
		
		model.addAttribute("store",store);
		return "store/storeView"; //승인상태일 때, 매장정보관리 페이지로 이동
	}
	
	@RequestMapping(value="/sm/store",method=RequestMethod.POST)
	public String insert(StoreInsertCommand command,Model model,HttpSession session) {
		StoreVO store = new StoreVO();
		UserVO user = (UserVO)session.getAttribute("loginUser");
		
		String storeCategoryName = command.getCategory();
		String storeCategoryId = storeCategoryDao.selectIdByName(storeCategoryName);
		StoreCategoryVO storeCategory = new StoreCategoryVO(storeCategoryId,storeCategoryName);
		
		store.setUser(user);
		
		store.setCategory(storeCategory);
		
		StoreStateVO storeState = new StoreStateVO();
		storeState.setId(1);
		store.setState(storeState);
		
		store.setBusinessNumber(command.getBusinessNumber());
		store.setName(command.getName());
		store.setAddress(command.getAddress());
		store.setSubAddress(command.getSubAddress());
		
		double storeLatitude = Double.parseDouble(command.getLatitude());
		double storeLongitude = Double.parseDouble(command.getLongitude());
		store.setLatitude(storeLatitude);//store.setLatitude(command.getLatitude());
		store.setLongitude(storeLongitude);//store.setLongitude(command.getLongitude());
		store.setPhone(command.getPhone());
		store.setOperateTime(command.getOperateTime());
		store.setPhoto(command.getPhoto().getOriginalFilename());
		store.setIntroduction(command.getIntroduction());
		
		
		storeInsertService.execute(session, store, command.getPhoto());
		model.addAttribute("store",store);
		return "store/storeHome";
	}
	
	@RequestMapping(value="/sm/delete/store" , method=RequestMethod.GET)
	public String delete(HttpSession session,Model model) {
		UserVO user = (UserVO)session.getAttribute("loginUser");
		int userId = user.getId();
		StoreVO store = null;
		store = storeViewByStoreService.execute(userId);
		int storeId = store.getId();
		
		storeDeleteService.execute(storeId);
		
		return "store/storeHome";
	}
	
	
	@RequestMapping(value="/sm/update/store", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		UserVO user = (UserVO)session.getAttribute("loginUser");
		int userId = user.getId();
		StoreVO store = null;
		store = storeViewByStoreService.execute(userId);
		model.addAttribute("store",store);
		
		return "store/storeUpdate";
	}
	
	@RequestMapping(value= "/sm/update/store", method=RequestMethod.POST)
	public String update(StoreUpdateCommand command, HttpSession session, Model model) {
		
		
		UserVO user = (UserVO)session.getAttribute("loginUser");
		int storeId = storeDao.selectByUserId(user.getId()).getId();
		String storeCategoryName = command.getCategory();
		
		String storeCategoryId = storeCategoryDao.selectIdByName(storeCategoryName);
		StoreCategoryVO storeCategory = new StoreCategoryVO(storeCategoryId,storeCategoryName);
//		StoreCategoryVO storeCategory = StoreCategoryVO.builder()
//				.id(storeCategoryId)
//				.name(storeCategoryName)
//				.build();
		
		StoreStateVO storeState = null;
		if(storeDao.selectByUserId(user.getId()).getState().getName().equals("승인")) {
			storeState = new StoreStateVO(2,"승인"); // 승인상태일 때 수정시 그대로 승인상태
		}
		else if(storeDao.selectByUserId(user.getId()).getState().getName().equals("거부")) {
			storeState = new StoreStateVO(1,"신청"); //거부상태일 때 수정시 신청상태로 변경
		}
		
		String storeBusinessNumber = command.getBusinessNumber();
		String storeName = command.getName();
		String storeAddress= command.getAddress();
		String storeSubAddress = command.getSubAddress();
		
		double storeLatitude = Double.parseDouble(command.getLatitude());
		double storeLongitude = Double.parseDouble(command.getLongitude());
		String storePhone = command.getPhone();
		String storeOperateTime = command.getOperateTime();
		String storePhoto = command.getPhoto().getOriginalFilename();
		String storeIntroduction = command.getIntroduction();
		OpenState openState = OpenState.CLOSE;
		String storePayNumber = "00000000"; // 업데이트 메서드에서 안씀
		
		StoreVO store = new StoreVO(storeId,user,storeCategory,storeState,storeBusinessNumber,storeName,storeAddress,storeSubAddress,
							storeLatitude,storeLongitude,storePhone,storeOperateTime,storePhoto,storeIntroduction,openState,storePayNumber);
		
		storeUpdateService.execute(session,store,command.getPhoto());
		model.addAttribute("store",store);
		
		return "store/storeHome";
	}
	
	@RequestMapping(value="/sm/switchOpenState/{openState}/store", method=RequestMethod.GET)
	public String updateOpenState(@PathVariable("openState") OpenState openState, HttpSession session, Model model) {
		UserVO user = (UserVO)session.getAttribute("loginUser");
		int storeId = storeDao.selectByUserId(user.getId()).getId();
	
		storeOpenStateService.execute(storeId, openState);
		StoreVO store = storeDao.selectById(storeId);
		model.addAttribute("store",store);
		return "store/storeHome";
	}
	
	
	
	
	@RequestMapping(value="/gm/{category}/{keyword}/{page}/store",method=RequestMethod.GET)
	public String listByUser(@PathVariable("category") String category, @PathVariable("keyword") String keyword,
							@PathVariable("page") String page, Model model, HttpSession session) {
		int intPage = Integer.parseInt(page);
		UserVO user = (UserVO)session.getAttribute("loginUser");
		double userLatitude = user.getLatitude();
		double userLongitude = user.getLongitude();
		
		List<StoreListByUserViewVO> storeListByUserViews =null;
		try {
			storeListByUserViews = storeListByUserService.execute(category,keyword,userLatitude,userLongitude,intPage);
		} catch (StoreException | ReviewException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PageVO> pages = storeListByUserService.getPages(category, keyword, userLatitude, userLongitude, intPage);
		model.addAttribute("category",category);
		model.addAttribute("keyword",keyword);
		model.addAttribute("stores",storeListByUserViews); //이름주의! stores로 담아놓음
		model.addAttribute("pages", pages);
		return "user/storeList";
	}

	@RequestMapping(value="/ad/{store-state-name}/{store-name}/{page}/store",method=RequestMethod.GET)
	public String listByManager(@PathVariable("store-state-name") String storeStateName, @PathVariable("store-name") String storeName, @PathVariable("page") String page, HttpSession session, Model model) {
		int intPage = Integer.parseInt(page);
		List<StoreVO> stores;
		List<PageVO> pages;
		
		UserVO user = (UserVO)session.getAttribute("loginUser");
		
		model.addAttribute("storeStateName",storeStateName);
		model.addAttribute("storeName",storeName);
		model.addAttribute("page",page);

		stores = storeListByManagerService.execute(storeStateName, storeName, intPage);
		pages = storeListByManagerService.getPages(storeStateName, storeName, intPage);
		
		model.addAttribute("stores",stores);
		model.addAttribute("type", storeStateName);
		model.addAttribute("pages", pages);
		
		return "manage/storeList";
	}

	@RequestMapping(value = "/gm/{id}/{type}/store", method = RequestMethod.GET)
	public String viewByUser(@PathVariable("id") String id, HttpSession session, Model model, @PathVariable("type") String type){
		int storeId = Integer.parseInt(id);
		UserVO user = (UserVO) session.getAttribute("loginUser");
		model.addAttribute("address", user.getAddress());

		StoreVO store = storeViewByUserService.execute(storeId);
		model.addAttribute("store", store);

		double total_score = reviewListService.getTotalScore(storeId);
		model.addAttribute("score", total_score);

		model.addAttribute("type", type);

		List<MenuVO> list = storeViewByUserService.getMenus(storeId);
		model.addAttribute("list", list);

		List<ReviewVO> reviews = reviewListService.execute(storeId, 1);
		model.addAttribute("reviews", reviews);

		return "user/storeView";
	}

	@RequestMapping(value = "/ad/{id}/store", method = RequestMethod.GET)
	public String viewByManager(@PathVariable("id") String id, Model model, HttpSession session){
		int storeId = Integer.parseInt(id);
		UserVO user = (UserVO) session.getAttribute("loginUser");
		if (user == null){
			return "redirect:/";
		}

		if (!user.getType().getId().equals("AD")){
			return "redirect:/";
		}

		StoreVO store = storeViewByUserService.execute(storeId);
		System.out.println(store);
		model.addAttribute("store", store);

		return "manage/storeView";
	}

}
