package kr.co.dinner41.service.manager;

import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

public interface ManagerApproveService {
    void execute(UserVO manager, int storeId);
}
