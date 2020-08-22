package kr.co.dinner41.service.order;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.Menu2OrderDao;
import kr.co.dinner41.dao.OrderDao;
import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.QnAException;
import kr.co.dinner41.exception.order.OrderException;
import kr.co.dinner41.vo.Menu2OrderViewVO;
import kr.co.dinner41.vo.OrderViewVO;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

@Service("orderList")
public class OrderListServiceImpl implements OrderListService {

	@Autowired
	@Qualifier("orderDao")
	private OrderDao oDao;

	@Autowired
	@Qualifier("storeDao")
	private StoreDao sDao;

	@Autowired
	@Qualifier("menu2orderDao")
	private Menu2OrderDao m2oDao;

	public static final int PAGE_SIZE = 10;
	public static final int NUMBER_OF_PAGE_IN_ONE_PAGE = 4;

	@Override
	public LinkedHashMap<OrderViewVO, List<Menu2OrderViewVO>> execute(UserVO user, String type, int page) {

		LinkedHashMap<OrderViewVO, List<Menu2OrderViewVO>> map = new LinkedHashMap<>();
		List<OrderViewVO> orderList = null;
		List<Menu2OrderViewVO> menuList = null;

		String userType = user.getType().getId();
		int userId = user.getId();
		int totalRecord = 0;

		if (userType.equals("GM")) {
				orderList = oDao.selectAllOrderByUser(page, PAGE_SIZE, userId, type);
				totalRecord = oDao.getTotalRecordForUser(userId, userType);
				menuList = m2oDao.selectAll();
				
				System.out.println("gm totalRecord :" + totalRecord);
				System.out.println("gm orderList : " + orderList);

				for (OrderViewVO order : orderList) {
					int orderId = order.getOrderId();
					List<Menu2OrderViewVO> tmp = new ArrayList<>();

					for (Menu2OrderViewVO m2o : menuList) {
						if (m2o.getOrderId() == orderId) {
							tmp.add(m2o);
						}
					}
					map.put(order, tmp);
				}

		} else if (userType.equals("SM")) {
			StoreVO store = sDao.selectByUserId(userId);
			int storeId = store.getId();

			orderList = oDao.selectAllOrderByStore(page, PAGE_SIZE, storeId, type);
			oDao.getTotalRecordForStore(storeId, type);
			menuList = m2oDao.selectAll();
			System.out.println("sm totalRecord :" + totalRecord);
			System.out.println("sm orderList : " + orderList);

			for (OrderViewVO order : orderList) {
				int orderId = order.getOrderId();
				List<Menu2OrderViewVO> tmp = new ArrayList<>();

				for (Menu2OrderViewVO m2o : menuList) {
					if (m2o.getOrderId() == orderId) {
						tmp.add(m2o);
					}
				}
				map.put(order, tmp);
			}
		}

		return map;
	}

	@Override
	public List<PageVO> getPages(int nowPage, String type, UserVO user) {
		List<PageVO> list = new ArrayList<>();
		int totalRecord = 0;
		String userType = user.getType().getId();
		int userId = user.getId();

		StoreVO store = null;
		int storeId = 0;

		if (type.equals("COMP")) {
			if(userType.equals("SM")){
				try {
					store =sDao.selectByUserId(userId);
					storeId =store.getId();
					totalRecord = oDao.getTotalRecordForStore(storeId, type);
					System.out.println("COM" + totalRecord);
				} catch (OrderException e) {
					e.printStackTrace();
				}
			}else{
				try {
					totalRecord = oDao.getTotalRecordForUser(userId, type);
					System.out.println("COM" + totalRecord);
				} catch (OrderException e) {
					e.printStackTrace();
				}
			}

		} else if (type.equals("WAIT")) {
			if (userType.equals("SM")){
				try {
					store =sDao.selectByUserId(userId);
					storeId =store.getId();
					totalRecord = oDao.getTotalRecordForStore(storeId, type);
					System.out.println("WAIT" + totalRecord);
				} catch (OrderException e) {
					e.printStackTrace();
				}
			}else{
				try {
					totalRecord = oDao.getTotalRecordForUser(userId, type);
					System.out.println("COM" + totalRecord);
				} catch (OrderException e) {
					e.printStackTrace();
				}
			}

		} else {
			if (userType.equals("SM")){
				try {
					store =sDao.selectByUserId(userId);
					storeId =store.getId();
					totalRecord = oDao.getTotalRecordForStore(storeId, type);
					System.out.println("ELSE" + totalRecord);
				} catch (OrderException e) {
					e.printStackTrace();
				}
			}else{
				try {
					totalRecord = oDao.getTotalRecordForUser(userId, type);
					System.out.println("COM" + totalRecord);
				} catch (OrderException e) {
					e.printStackTrace();
				}
			}
		}

		int totalPage = totalRecord / PAGE_SIZE;
		if ((totalRecord % PAGE_SIZE) != 0) {
			totalPage = totalPage + 1;
		}

		if (nowPage > totalPage) {
			nowPage = totalPage;
		}
		if (nowPage < 1) {
			nowPage = 1;
		}

		int startPoint = nowPage / NUMBER_OF_PAGE_IN_ONE_PAGE;
		int startPage = startPoint * NUMBER_OF_PAGE_IN_ONE_PAGE + 1;

		int endPage = startPage + NUMBER_OF_PAGE_IN_ONE_PAGE - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		int first = 0;
		if (startPage > 1) {
			first = startPage - 1;
		}
		if (startPage <= 1) {
			first = 1;
		}
		PageVO firstPage = new PageVO("<<", first);

		int last = 0;
		if (endPage < totalPage) {
			last = endPage + 1;
		}
		if (endPage >= totalPage) {
			last = endPage;
		}
		PageVO lastPage = new PageVO(">>", last);

		list.add(firstPage);
		for (int i = startPage; i <= endPage; i++) {
			PageVO pageVO = new PageVO("" + i, i);
			list.add(pageVO);
		}
		list.add(lastPage);

		return list;
	}
}
