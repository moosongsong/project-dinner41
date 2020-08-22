package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.exception.store.StoreCategoryException;
import kr.co.dinner41.vo.StoreCategoryVO;

public interface StoreCategoryDao {
	void insert(StoreCategoryVO storeCategory) throws StoreCategoryException;
	void delete(String id) throws StoreCategoryException;
	String selectIdByName(String name) throws StoreCategoryException;
	StoreCategoryVO selectById(String id) throws StoreCategoryException;
	List<StoreCategoryVO> selectAll() throws StoreCategoryException;
}
