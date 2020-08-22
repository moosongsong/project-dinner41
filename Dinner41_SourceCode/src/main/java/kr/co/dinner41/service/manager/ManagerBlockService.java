package kr.co.dinner41.service.manager;

import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;

public interface ManagerBlockService {
    void execute(UserVO manager, int storeId, String content);
}
