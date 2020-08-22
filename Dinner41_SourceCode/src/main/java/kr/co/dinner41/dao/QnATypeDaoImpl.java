package kr.co.dinner41.dao;

import kr.co.dinner41.exception.QnATypeException;
import kr.co.dinner41.exception.QnATypeSelectException;
import kr.co.dinner41.mapper.QnATypeMapper;
import kr.co.dinner41.vo.QnATypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qnATypeDao")
public class QnATypeDaoImpl implements QnaTypeDao{
    @Autowired
    private JdbcTemplate template;

    @Override
    public void insert(QnATypeVO qnAType) throws QnATypeException {
        return;
    }

    @Override
    public void delete(String id) throws QnATypeException {
        return;
    }

    @Override
    public void update(String id, String name) throws QnATypeException {
        return;
    }

    @Override
    public QnATypeVO selectById(String id) throws QnATypeSelectException {
        List<QnATypeVO> list = null;
        String sql = "SELECT * FROM qna_types WHERE qna_type_id = ?;";
        list = template.query(sql, new QnATypeMapper(), id);
        return (list.size() == 0? null:list.get(0));
    }

    @Override
    public List<QnATypeVO> selectAll() throws QnATypeException {
        List<QnATypeVO> list = null;
        String sql = "SELECT * FROM qna_types;";
        list = template.query(sql, new QnATypeMapper());
        return list;
    }
}
