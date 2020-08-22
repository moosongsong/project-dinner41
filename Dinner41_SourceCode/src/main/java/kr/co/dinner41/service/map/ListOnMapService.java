package kr.co.dinner41.service.map;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.co.dinner41.exception.map.MapException;
import kr.co.dinner41.vo.StoreVO;

public interface ListOnMapService {
	List<StoreVO> execute(HttpServletRequest request)throws MapException;

}
