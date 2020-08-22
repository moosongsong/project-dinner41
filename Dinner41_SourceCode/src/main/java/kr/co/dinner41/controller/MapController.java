package kr.co.dinner41.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dinner41.exception.map.SearchStoreFailedException;
import kr.co.dinner41.service.map.ListOnMapService;
import kr.co.dinner41.vo.StoreVO;

@Controller
public class MapController {
	@Autowired
	@Qualifier("listOnMapService")
	private ListOnMapService listOnMapSerivce;
	
	
	@RequestMapping(value="/gm/map",method=RequestMethod.GET)
	public String loadMap() {
		System.out.println("지도 컨트롤러 get진입");
		return "user/userMap";
	}
	
	@ResponseBody
	@RequestMapping(value="/gm/map",method=RequestMethod.POST)
	public HashMap<String,Object> loadMap(HttpServletRequest request){
		//HttpSession session=null;
		System.out.println("지도 컨트롤러 post진입");
		HashMap<String,Object> result=new HashMap<>();
		try {
			//System.out.println(request.getParameter("latitude"));
			//System.out.println(request.getParameter("longitude"));
			List<StoreVO> storeList=listOnMapSerivce.execute(request);
			System.out.println(storeList);
			result.put("result",true);
			result.put("storeList", storeList);
		}
		catch(SearchStoreFailedException e) {
			System.out.println(e.getMessage());
			result.put("result",false);
		}
		return result;
	}
	

}
