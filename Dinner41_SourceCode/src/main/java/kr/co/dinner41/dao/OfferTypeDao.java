package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.exception.menu.OfferTypeException;
import kr.co.dinner41.vo.OfferTypeVO;

public interface OfferTypeDao {
	
	  void insert(OfferTypeVO offerType) throws OfferTypeException;
	    void delete(String id) throws OfferTypeException;
	    void update(String id, String name) throws OfferTypeException;
	    OfferTypeVO selectById(String id) throws OfferTypeException;
	    OfferTypeVO selectByName(String name) throws OfferTypeException;
	    List<OfferTypeVO> selectAll() throws OfferTypeException;

}
