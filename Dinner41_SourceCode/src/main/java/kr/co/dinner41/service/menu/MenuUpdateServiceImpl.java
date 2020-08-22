package kr.co.dinner41.service.menu;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.dinner41.command.MenuUpdateCommand;
import kr.co.dinner41.dao.MenuDaoImpl;
import kr.co.dinner41.dao.OfferTypeDaoImpl;
import kr.co.dinner41.dao.StoreDaoImpl;
import kr.co.dinner41.exception.menu.MenuException;
import kr.co.dinner41.exception.menu.OfferTypeSelectException;
import kr.co.dinner41.fileuploader.FileWriter;
import kr.co.dinner41.vo.MenuVO;
import kr.co.dinner41.vo.OfferTypeVO;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

@Service("menuUpdateService")
public class MenuUpdateServiceImpl implements MenuUpdateService {

	@Autowired
	private MenuDaoImpl menuDao;
	
	@Autowired
	private OfferTypeDaoImpl offerTypeDao;
	
	@Autowired
	private StoreDaoImpl storeDao;
	
	@Override
	public void execute(MenuUpdateCommand command, int storeId, int menuId, UserVO user,HttpSession session) throws MenuException {
		
		OfferTypeVO offerTypeVO = null;
		StoreVO storeVO = null;
		MenuVO menuVO = null;
		
		try {
			offerTypeVO = offerTypeDao.selectById(command.getType());
			storeVO = storeDao.selectByUserId(user.getId());
			
		}
		catch (OfferTypeSelectException e) {
			e.printStackTrace();
		}
		
		MenuVO menu = new MenuVO();
		
		
		menu.setStore(storeVO);
		menu.setId(menuId);
		menu.setOfferType(offerTypeVO);
		menu.setName(command.getName());
		menu.setPrice(command.getPrice());
		menu.setPhoto(command.getPhoto().getOriginalFilename());
		menu.setAmount(command.getAmount());
		menu.setTag(command.getTag());
		menu.setDescription(command.getDescription());
		menu.setNotice(command.getNotice());
		
		MultipartFile file = command.getPhoto();
		
		try {
			
			if(menuDao.update(menu, storeVO) != 0) {
				FileWriter fileWriter = new FileWriter();
				String path = session.getServletContext().getRealPath("/");
				System.out.println(path+"resources\\images\\");
				fileWriter.writeFile(file,path+"resources\\images\\",file.getOriginalFilename());
			}
		}
		catch(MenuException e)
		{
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
	}

}
