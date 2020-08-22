package kr.co.dinner41.service.order;

import java.util.HashMap;
import java.util.List;

import kr.co.dinner41.vo.Menu2OrderViewVO;
import kr.co.dinner41.vo.OrderViewVO;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.UserVO;

public interface OrderListService {
	public HashMap<OrderViewVO, List<Menu2OrderViewVO>> execute(UserVO user, String type, int page);
    List<PageVO> getPages(int nowPage, String type, UserVO user);

}
