package kr.co.dinner41.dao;

import kr.co.dinner41.vo.LogVO;

public interface LogDao {
	void insert(LogVO log);
	void delete(int id);
	void updateMessage(int id,String message);
	void updateManager(int id,int managerId);
	void selectByManagerId(int managerId);
	String selectByStoreIdAndStoreStateId(int storeId,int storeStateId);
}
