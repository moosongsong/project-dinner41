package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.vo.Log2ManagerVO;

public interface Log2ManagerDao {
	void insert(Log2ManagerVO log2manager);
	void delete(int logId);
	void update(int logId,int managerId);
	List<Log2ManagerVO> selectAll();
}
