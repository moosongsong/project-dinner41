package kr.co.dinner41.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dinner41.dao.MenuDao;
import kr.co.dinner41.dao.OrderDao;
import kr.co.dinner41.service.menu.MenuUpdateAmountService;
import kr.co.dinner41.service.order.OrderDeleteService;
import kr.co.dinner41.service.order.OrderInsertService;
import kr.co.dinner41.service.order.OrderListService;
import kr.co.dinner41.service.order.OrderReceiptCompleteService;
import kr.co.dinner41.service.order.OrderViewService;
import kr.co.dinner41.vo.Menu2OrderViewVO;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.OrderVO;
import kr.co.dinner41.vo.OrderViewVO;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.UserVO;

@Controller
public class OrderController {

	@Autowired
	@Qualifier("orderInsert")
	OrderInsertService insertService;

	@Autowired
	@Qualifier("orderDelete")
	OrderDeleteService deleteService;

	@Autowired
	@Qualifier("orderReceiptComplete")
	OrderReceiptCompleteService receiptCompleteService;
	
	@Autowired
	@Qualifier("orderList")
	OrderListService listService;

	@Autowired
	@Qualifier("orderView")
	OrderViewService viewService;
	
	@Autowired
	@Qualifier("menuUpdateAmountService")
	MenuUpdateAmountService updateSerivce;

	@Autowired
	@Qualifier("orderDao")
	OrderDao orderDao;

	@Autowired
	@Qualifier("menuDao")
	MenuDao mDao;


	@RequestMapping(value = "/gm/order", method = RequestMethod.GET)
	public String insert() {
		return "user/orderInsert";
	}

	@ResponseBody
	@RequestMapping(value = "/gm/order", method = RequestMethod.POST)
	public HashMap<String, Object> insert(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession(false);
		String [] arrForOrder = (String[])session.getAttribute("arrForOrder");
		String [] arrForOrder2 = (String[])session.getAttribute("arrForOrder2");
		UserVO user = (UserVO)session.getAttribute("loginUser");
		int userId = user.getId();
		
		String getTime = request.getParameter("getTime");
		String payTotal = request.getParameter("payTotal");
		payTotal = payTotal.replaceAll("[^0-9]", "");
		
		String[] payNumberAndOrderId = insertService.execute(arrForOrder, arrForOrder2, userId, Integer.parseInt(getTime), Integer.parseInt(payTotal));
		String payNumber = payNumberAndOrderId[0];
		String orderId = payNumberAndOrderId[1];
		String price = arrForOrder[Integer.parseInt(arrForOrder[1])+2];

		// 결제 진행을 위해 매장결제식별번호, 주문번호, 결제금액, 사용자 정보, 매장아이디를 보내줌
		map.put("storePayNumber", payNumber);
		map.put("orderId", orderId);
		map.put("price", price);
		map.put("user", user);
		map.put("storeId", arrForOrder[0]);
	
		// 결제가 완료되면 완료된 메뉴들을 장바구니에서 제거하기위해 메뉴정보를 보내줌
		int clearMenuCount = Integer.parseInt(arrForOrder[1]);
		String [] clearMenuIds = new String[clearMenuCount]; 
		for (int i = 0; i < clearMenuCount; i++) {
			clearMenuIds[i] = arrForOrder[i+2];
		}

		map.put("menuIds", clearMenuIds);
		return map;
	}

	
	@RequestMapping(value = "/gm/{type}/{page}/order", method = RequestMethod.GET)
	public String listByUser(@PathVariable("page") int page, @PathVariable("type") String type, HttpSession session, Model model) {

		UserVO user = (UserVO)session.getAttribute("loginUser");
		Map<OrderViewVO, List<Menu2OrderViewVO>> map = null;
		List<PageVO> pageList = null;

		// 한 page에 해당하는 주문목록(HashMap형태)을 가져옴
		// type에는 WAIT(주문대기), COMP(수령완료)이 있음
		map = listService.execute(user, type, page);
		pageList = listService.getPages(page, type, user);

		model.addAttribute("map", map);
		model.addAttribute("pages", pageList);
		model.addAttribute("type", type);
		model.addAttribute("page",page);
		return "user/orderList";
	}

	
	@RequestMapping(value = "/sm/{type}/{page}/order", method = RequestMethod.GET)
	public String listByStore(@PathVariable("page") int page, @PathVariable("type") String type, HttpSession session, Model model) {

		UserVO user = (UserVO)session.getAttribute("loginUser");
		HashMap<OrderViewVO, List<Menu2OrderViewVO>> map = null;
		List<PageVO> pageList = null;

		
		// 한 page에 해당하는 주문목록(HashMap형태)을 가져옴
		// type에는 WAIT(주문대기), COMP(수령완료)이 있음
		map = listService.execute(user, type, page);
		pageList = listService.getPages(page, type, user);
		
		model.addAttribute("map", map);
		model.addAttribute("pages", pageList);
		model.addAttribute("type", type);
		model.addAttribute("page",page);
		return "store/orderList";
	}

	
	@RequestMapping(value = "/gm/{id}/order/detail", method = RequestMethod.GET)
	public String viewByUser(@PathVariable("id") int orderId, Model model) {
		OrderVO order = viewService.execute(orderId);
		model.addAttribute("order", order);
		return "user/orderView";
	}

	
	@RequestMapping(value = "/sm/{id}/order/detail", method = RequestMethod.GET)
	public String viewByStore(@PathVariable("id") int orderId, Model model) {
		OrderVO order = viewService.execute(orderId);
		model.addAttribute("order", order);
		return "store/orderView";
	}
	

	@ResponseBody
	@RequestMapping(value = "/sm/order/complete", method = RequestMethod.GET)
	public HashMap<String, Object> receiptComplete(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		receiptCompleteService.execute(orderId);
		
		map.put("result", true);
		map.put("msg", "수령처리가 완료되었습니다!!");
		return map;
	}

	// 결제가 완료되면 해당 제품의 수량을 하나줄임
	@ResponseBody
	@RequestMapping(value = "/gm/{storeId}/order/updateMenuAmount", method = RequestMethod.PUT)
	public HashMap<String, Object> updateAmount(@PathVariable("storeId") int storeId, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String [] menuIds = request.getParameterValues("arr[]");
		
		System.out.println("결제한 메뉴의 개수 : " + menuIds.length);
		System.out.print("메뉴 아이디 : ");
		for (int i = 0; i < menuIds.length; i++) {
			System.out.print("/" + menuIds[i]);
			int count = 0;
			try {
				MenuVO menu = mDao.selectByMenuIdStoreId(Integer.parseInt(menuIds[i]), storeId);
				count = menu.getAmount();
				updateSerivce.execute(storeId, Integer.parseInt(menuIds[i]), --count);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println();

		return map;
	}
	
	
	@RequestMapping(value = "/gm/{id}/order/delete", method = RequestMethod.GET)
	public HashMap<String, Object> delete(@PathVariable("id") int orderId) {
		HashMap<String, Object> map = new HashMap<>();
		// 넘어온 주문 아이디로 삭제를 진행
		return map;
	}
	
	
	@RequestMapping(value = "/gm/pay", method = RequestMethod.GET)
	public String pay() {
		return "user/pay";
	}
	
}
