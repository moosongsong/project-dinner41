package kr.co.dinner41.service.store;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.fileuploader.FileWriter;
import kr.co.dinner41.vo.StoreVO;

@Service("storeUpdateService")
public class StoreUpdateServiceImpl implements StoreUpdateService {
	@Autowired
	@Qualifier("storeDao")
	StoreDao storeDao;
	
	@Override
	public void execute(HttpSession session,StoreVO store,MultipartFile file) throws StoreException {
		
		if(storeDao.update(store)!=0) {
			FileWriter fileWriter = new FileWriter();
			String path = session.getServletContext().getRealPath("/");
			System.out.println(path+"resources\\images\\");
			fileWriter.writeFile(file,path+"resources\\images\\",file.getOriginalFilename());
		}

	}

}
