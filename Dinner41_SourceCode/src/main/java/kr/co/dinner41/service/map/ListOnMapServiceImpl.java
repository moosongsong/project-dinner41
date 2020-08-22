package kr.co.dinner41.service.map;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.map.MapException;
import kr.co.dinner41.exception.map.SearchStoreFailedException;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.LocationVO;
import kr.co.dinner41.vo.StoreVO;

@Service("listOnMapService")
public class ListOnMapServiceImpl implements ListOnMapService {
	@Autowired
	@Qualifier("storeDao")
	private StoreDao storeDao;

	@Override
	public List<StoreVO> execute(HttpServletRequest request) throws MapException {
		System.out.println("서비스 진입");
		double latitude=Double.parseDouble(request.getParameter("latitude"));
		double longitude=Double.parseDouble(request.getParameter("longitude"));
		String keyword=(String)request.getParameter("keyword");
		
		System.out.println(latitude);
		System.out.println(longitude);
		System.out.println(keyword);

		List<StoreVO> storeList=null;
		
		try {
			storeList=storeDao.selectByCategoryNameOnMap(keyword,latitude, longitude);
			System.out.println("system:"+storeList);
		}
		catch(StoreException e) {
			System.out.println(e.getMessage());
			throw new SearchStoreFailedException();
		}
		return storeList;
	}

}
