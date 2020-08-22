package kr.co.dinner41.service.manager;

import kr.co.dinner41.dao.StoreDao;
import kr.co.dinner41.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("managerRejectService")
public class ManagerRejectServiceImpl implements ManagerRejectService {
    @Autowired
    @Qualifier("storeDao")
    StoreDao storeDao;

    @Override
    public void execute(UserVO manager, int storeId, String content) {
        storeDao.reject(storeId, manager, content);
    }
}
