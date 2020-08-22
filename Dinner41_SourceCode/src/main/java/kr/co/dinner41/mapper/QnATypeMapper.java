package kr.co.dinner41.mapper;

import kr.co.dinner41.vo.QnATypeVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QnATypeMapper implements RowMapper<QnATypeVO> {
    @Override
    public QnATypeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("qna_type_id");
        String name = rs.getString("qna_type_name");
        return new QnATypeVO(id, name);
    }
}
