package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.exception.store.StoreStateException;
import kr.co.dinner41.vo.StoreStateVO;

public interface StoreStateDao {
	void insert(StoreStateVO storeState) throws StoreStateException;
	void delete(int id) throws StoreStateException;
	StoreStateVO selectById(int id) throws StoreStateException;
	List<StoreStateVO> selectAll() throws StoreStateException;
}
