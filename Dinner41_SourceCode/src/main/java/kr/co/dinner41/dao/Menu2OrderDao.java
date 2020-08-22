package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.vo.Menu2OrderVO;
import kr.co.dinner41.vo.Menu2OrderViewVO;

public interface Menu2OrderDao {
	void insert(Menu2OrderVO menu2order);
	void delete(Menu2OrderVO menu2order);
	public List<Menu2OrderViewVO> selectAll();
	public List<Menu2OrderViewVO> selectAllMenu2OrderView(int orderId); 
}
