package kr.co.dinner41.service.manager;

import kr.co.dinner41.vo.UserVO;

public interface ManagerRejectService {
    void execute(UserVO manager, int storeId, String content);
}
