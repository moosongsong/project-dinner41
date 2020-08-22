package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.*;


public interface StoreDao {
	int insert(StoreVO store) throws StoreException;
	void deleteByStore(int id) throws StoreException;
	void deleteByManager(int storeId, UserVO manager, String content) throws StoreException;
	int update(StoreVO store) throws StoreException;
	void switchOpenState(int storeId,OpenState openState) throws StoreException;
	StoreVO selectById(int id) throws StoreException;
	StoreVO selectByUserId(int userId) throws StoreException;
	List<StoreVO> selectByCategoryName(String categoryName,double userLatitude, double userLongitude, int page, int pageSize) throws StoreException;
	List<StoreVO> selectByCategoryNameOnMap(String categoryName,double userLatitude, double userLongitude) throws StoreException;
	List<StoreVO> selectByStateName(String stateName, int page, int pageSize) throws StoreException;
	List<StoreVO> selectByStateNameAndName(String stateName,String name,int page,int pageSize) throws StoreException;
	StoreVO selectByBusinessNumber(String businessNumber) throws StoreException;
	List<StoreVO> selectByName(String name,double userLatitude, double userLongitude, int page, int pageSize) throws StoreException;
	List<StoreVO> selectByLocation(double userLatitude, double userLongitude) throws StoreException;
	List<StoreVO> selectByOpenState(OpenState openState, int page, int pageSize) throws StoreException;
	List<StoreVO> selectAll(int page,int pageSize) throws StoreException;
	List<StoreListByUserViewVO> selectViewByCategoryName(String categoryName, double userLatitude, double userLongitude,int page, int pageSize) throws StoreException;
	List<StoreListByUserViewVO> selectViewByStoreNameOrMenuName(String keyword, double userLatitude, double userLongitude,int page, int pageSize) throws StoreException;
	List<MenuVO> getMenus(int storeId) throws StoreException;
	void approve(int storeId, UserVO manager) throws StoreException;
	void block(int storeId, UserVO manager, String content) throws StoreException;
	void reject(int storeId, UserVO manager, String content) throws StoreException;
	int getTotalCount(String stateName,String name) throws StoreException;

	int getTotalCountByCategoryName(String categoryName, double userLatitude, double userLongitude, int page, int pageSize) throws StoreException;

	int getTotalCountByStoreNameOrMenuName(String keyword, double userLatitude, double userLongitude, int page, int pageSize) throws StoreException;
}
