package kr.co.dinner41.service.store;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.StoreVO;

@Service("storeViewByManagerService")
public class StoreViewByManagerServiceImpl implements StoreViewByManagerService {
	@Autowired
	@Qualifier("storeDao")
	StoreDao storeDao;
	
	@Override
	public StoreVO execute(HttpSession session) throws StoreException {
		// TODO Auto-generated method stub
		return null;
	}

}
