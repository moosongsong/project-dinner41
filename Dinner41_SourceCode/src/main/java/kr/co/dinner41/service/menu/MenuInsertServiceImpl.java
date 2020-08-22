package kr.co.dinner41.service.menu;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.dinner41.command.MenuInsertCommand;
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

@Service("menuInsertService")
public class MenuInsertServiceImpl implements MenuInsertService {

	@Autowired
	@Qualifier("menuDao")
	private MenuDaoImpl menuDao;
	
	@Autowired
	@Qualifier("offerTypeDao")
	private OfferTypeDaoImpl offerTypeDao;	

	@Autowired
	private StoreDaoImpl storeDao;

	@Override
	public void execute(MenuInsertCommand command, UserVO user,HttpSession session) throws SQLException {
		
		OfferTypeVO offerTypeVO = null;
		StoreVO storeVO = null;
		int menuId = 0;

		try {
			offerTypeVO = offerTypeDao.selectById(command.getType());

			storeVO = storeDao.selectByUserId(user.getId());
			menuId = menuDao.getLastInsertId(storeVO.getId());

		}
		catch (OfferTypeSelectException e) {
			e.printStackTrace();
		}
		
		MenuVO menu = new MenuVO();
		menu.setStore(storeVO);
		menu.setId(menuId);
		menu.setOfferType(offerTypeVO);
		menu.setPhoto(command.getPhoto().getOriginalFilename());
		menu.setPrice(command.getPrice());
		menu.setName(command.getName());
		menu.setTag(command.getTag());
		menu.setAmount(command.getAmount());
		menu.setDescription(command.getIntroduction());
		menu.setNotice(command.getNotice());
		
		try {
			MultipartFile file = command.getPhoto();
			if(menuDao.insert(menu, storeVO)!=0) {
					FileWriter fileWriter = new FileWriter();
					String path = session.getServletContext().getRealPath("/");
					System.out.println(path+"resources\\images\\");
					fileWriter.writeFile(file,path+"resources\\images\\",file.getOriginalFilename());
				}
			}
		catch (MenuException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}


