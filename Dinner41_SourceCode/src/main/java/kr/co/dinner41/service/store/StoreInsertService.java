package kr.co.dinner41.service.store;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import kr.co.dinner41.exception.store.StoreException;
import kr.co.dinner41.vo.StoreVO;

public interface StoreInsertService {
	void execute(HttpSession session, StoreVO store, MultipartFile file) throws StoreException;
}
