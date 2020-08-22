package kr.co.dinner41.service.store;

import java.util.ArrayList;
import java.util.List;

import kr.co.dinner41.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.StoreVO;


@Service("storeListByManagerService")
public class StoreListByManagerServiceImpl implements StoreListByManagerService {
	public static final int PAGE_SIZE = 5;
	public static final int NUMBER_OF_PAGE_IN_ONE_PAGE = 4;

	@Autowired
	@Qualifier("storeDao")
	StoreDao storeDao;
	
	@Override
	public List<StoreVO> execute(String storeStateName,String storeName,int page) throws StoreException{
		List<StoreVO> stores= null;
		if(storeStateName.equals("all-") && storeName.equals("all-")) {
			stores = storeDao.selectByStateNameAndName("%", "%", page, PAGE_SIZE);
		}
		else if(storeStateName.equals("all-") && !storeName.equals("all-")) {
			stores=storeDao.selectByStateNameAndName("%", storeName, page, PAGE_SIZE);
		}
		else if(!storeStateName.equals("all-") && storeName.equals("all-")) {
			stores=storeDao.selectByStateNameAndName(storeStateName, "%", page, PAGE_SIZE);
		}
		else {
			stores = storeDao.selectByStateNameAndName(storeStateName, storeName, page, PAGE_SIZE);
		}
		return stores;
	}

	@Override
	public List<PageVO> getPages(String storeStateName, String storeName, int nowPage) {
		List<PageVO> list = new ArrayList<>();
		int totalRecord = 0;

		if(storeStateName.equals("all-") && storeName.equals("all-")) {
			totalRecord = storeDao.getTotalCount("%", "%");
		}
		else if(storeStateName.equals("all-") && !storeName.equals("all-")) {
			totalRecord = storeDao.getTotalCount("%", storeName);
		}
		else if(!storeStateName.equals("all-") && storeName.equals("all-")) {
			totalRecord = storeDao.getTotalCount(storeStateName, "%");
		}
		else {
			totalRecord = storeDao.getTotalCount(storeStateName, storeName);
		}

		int totalPage = totalRecord/PAGE_SIZE;
		if ((totalRecord%PAGE_SIZE) != 0){
			totalPage = totalPage+1;
		}

		if (nowPage > totalPage){
			nowPage = totalPage;
		}
		if (nowPage<1){
			nowPage = 1;
		}

		int startPoint = nowPage/ NUMBER_OF_PAGE_IN_ONE_PAGE;
		int startPage = startPoint* NUMBER_OF_PAGE_IN_ONE_PAGE + 1;

		int endPage = startPage + NUMBER_OF_PAGE_IN_ONE_PAGE - 1;
		if (endPage > totalPage){
			endPage = totalPage;
		}

		int first=0;
		if (startPage>1){
			first = startPage-1;
		}
		if (startPage<=1){
			first = 1;
		}
		PageVO firstPage = new PageVO("<<", first);

		int last = 0;
		if (endPage<totalPage){
			last = endPage +1;
		}
		if (endPage >= totalPage){
			last = endPage;
		}
		PageVO lastPage = new PageVO(">>", last);

		list.add(firstPage);
		for (int i=startPage; i<=endPage; i++){
			PageVO pageVO = new PageVO(""+i, i);
			list.add(pageVO);
		}
		list.add(lastPage);

		return list;
	}
}

