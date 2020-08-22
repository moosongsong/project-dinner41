package kr.co.dinner41.mapper;

import kr.co.dinner41.vo.OfferTypeVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferTypeMapper implements RowMapper<OfferTypeVO> {
    @Override
    public OfferTypeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("offer_type_id");
        String name = rs.getString("offer_type_name");
        return new OfferTypeVO(id, name);
    }
}
