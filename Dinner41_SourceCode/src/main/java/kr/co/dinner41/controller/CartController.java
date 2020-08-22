package kr.co.dinner41.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dinner41.service.cart.CartDeleteService;
import kr.co.dinner41.service.cart.CartInsertService;
import kr.co.dinner41.service.cart.CartListService;
import kr.co.dinner41.vo.CartVO;
import kr.co.dinner41.vo.UserTypeVO;
import kr.co.dinner41.vo.UserVO;

@Controller
public class CartController {

	@Autowired
	@Qualifier("cartInsert")
	CartInsertService insertService;
	
	@Autowired
	@Qualifier("cartDelete")
	CartDeleteService deleteService;

	@Autowired
	@Qualifier("cartList")
	CartListService listService;

	// 향후 삭제
	@RequestMapping(value = "/menuView", method = RequestMethod.GET)
	public String menuView() {
		return "user/menuView";
	}

	@ResponseBody
	@RequestMapping(value = "/gm/cart", method = RequestMethod.POST)
	public HashMap<String, Object> insert(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession(false);
		if (session == null) {
			map.put("result", false);
			map.put("msg", "로그인하지 않으면 장바구니를 사용할 수 없습니다.");
			return map;
		}
		
		@SuppressWarnings("unchecked")
		List<CartVO> carts = (List<CartVO>)session.getAttribute("carts");
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		int menuId = Integer.parseInt(request.getParameter("menuId"));
		
		// 장바구니가 비어있는 경우
		// 메뉴를 장바구니에 바로 담음
		if (carts.isEmpty()) {
			session.setAttribute("carts", new ArrayList<CartVO>());
			insertService.execute(session, storeId, menuId);
			map.put("msg2", "장바구니에 등록되었습니다!!");
		}
		// 장바구니가 비어있지 않은 경우
		// 같은 매장의 메뉴를 넣을 경우 장바구니에 메뉴 추가
		// 다른 매장의 메뉴를 넣을 경우 장바구니를 비우고 새로 메뉴 추가
		else {
			if (carts.get(0).getStoreId() == storeId) {
				boolean flag = false;
				for (int i = 0; i < carts.size(); i++) {
					// 장바구니에 이미 들어있는 메뉴를 다시 넣는 경우
					// 해당 메뉴는 담지 못하도록 막음
					if (carts.get(i).getMenuId() == menuId) {
						flag = true;
					}
				}

				if (flag == false) {
					insertService.execute(session, storeId, menuId);
					map.put("msg2", "장바구니에 등록되었습니다!!");
				}
				else {
					map.put("msg2", "이미 장바구니에 등록되어있는 메뉴입니다.");
				}
			}
			else {
				session.removeAttribute("carts");
				session.setAttribute("carts", new ArrayList<CartVO>());
				insertService.execute(session, storeId, menuId);
				map.put("msg1", "이전 매장의 장바구니 기록은 사라집니다.");
			}
		}
		
		map.put("result", true);
		return map;
	}


	@ResponseBody
	@RequestMapping(value = "/gm/cart", method = RequestMethod.DELETE)
	public HashMap<String, Object> delete(HttpSession session, HttpServletRequest request, CartVO cart) {

		HashMap<String, Object> map = new HashMap<>();
		String [] menuIds = request.getParameterValues("arr[]");

		deleteService.execute(session, menuIds);
		map.put("msg", "장바구니에서 삭제 되었습니다!!");
		return map;
	}

	
	@RequestMapping(value = "/gm/cart", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpSession session, Model model) {

		// 사용자가 로그인하지 않고 URL을 통해 오는 경우
		if (session == null) {
			System.out.println("CartController:list():현재 세션이 없습니다.");
			// 인터셉터로 보내야함
		}
		
		if (session.getAttribute("loginUser") == null) {
			System.out.println("CartController:list()"
					+ ": 세션은 있지만 안에 로그인 정보가 삭제되어있습니다."
					+ " 아마 서버를 재구동해서 세션은 남아있지만 세션안의 정보는 삭제된 상황일겁니다.");
		}

		// 로그인했지만 점주회원, 관리자가 해당 기능을 URL을 통해 사용하려고 하는 경우
		UserVO user = (UserVO)session.getAttribute("loginUser");
		UserTypeVO type = user.getType();
		if (type.getName().equals("AD") || type.getName().equals("SM")) {
			return "/sessionCheck";
		}
		
		@SuppressWarnings("unchecked")
		List<CartVO> carts = (List<CartVO>)session.getAttribute("carts");
		if (!carts.isEmpty()) {
			carts = listService.execute(carts);
		}
		model.addAttribute("carts", carts);
		return "user/cartView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/gm/cart/order", method = RequestMethod.POST)
	public HashMap<String, Object> cartOrder(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		
		// 장바구니에서 넘어온 주문 정보들
		String [] arrForOrder = request.getParameterValues("arr[]");
		// 장바구니에서 넘어온 선택된 메뉴들의 수량들
		String [] arrForOrder2 = request.getParameterValues("arr2[]");
		
		HttpSession session = request.getSession(false);
		session.setAttribute("arrForOrder", arrForOrder);
		session.setAttribute("arrForOrder2", arrForOrder2);
		map.put("msg", "주문 화면으로 넘어갑니다.");
		return map;
	}
}
