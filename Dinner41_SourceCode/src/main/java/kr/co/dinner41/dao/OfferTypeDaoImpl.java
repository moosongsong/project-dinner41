package kr.co.dinner41.dao;

import kr.co.dinner41.exception.menu.OfferTypeException;
import kr.co.dinner41.exception.menu.OfferTypeSelectException;
import kr.co.dinner41.mapper.OfferTypeMapper;
import kr.co.dinner41.vo.OfferTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("offerTypeDao")
public class OfferTypeDaoImpl implements OfferTypeDao{
    @Autowired
    private JdbcTemplate jTemp;

    @Override
    public void insert(OfferTypeVO offerType) throws OfferTypeException {
        return;
    }

    @Override
    public void delete(String id) throws OfferTypeException {
        return;
    }

    @Override
    public void update(String id, String name) throws OfferTypeException {
        return;
    }

    @Override
    public OfferTypeVO selectById(String id) throws OfferTypeSelectException {
        List<OfferTypeVO> list = null;
        String sql = "SELECT * FROM offer_types WHERE offer_type_id = ?";
        list = jTemp.query(sql, new OfferTypeMapper(), id);
        return (list.size() == 0? null:list.get(0));
    }

    @Override
    public List<OfferTypeVO> selectAll() throws OfferTypeException {
        List<OfferTypeVO> list = null;
        String sql = "SELECT * FROM offer_types;";
        list = jTemp.query(sql, new OfferTypeMapper());
        return list;
    }

	public OfferTypeVO selectByName(String name) throws OfferTypeSelectException  {
		List<OfferTypeVO> list = null;
        String sql = "SELECT * FROM offer_types WHERE offer_type_name = ?";
        list = jTemp.query(sql, new OfferTypeMapper(), name);
        return (list.size() == 0? null:list.get(0));
	}
}
