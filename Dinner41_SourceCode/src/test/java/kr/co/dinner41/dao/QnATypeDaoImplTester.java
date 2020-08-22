package kr.co.dinner41.dao;

import kr.co.dinner41.exception.QnATypeException;
import kr.co.dinner41.vo.QnATypeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class QnATypeDaoImplTester {
    @Autowired
    private ApplicationContext ctx;

    @Test
    public void testSelectAll() {
        QnATypeDaoImpl dao = ctx.getBean("qnATypeDao", QnATypeDaoImpl.class);
        List<QnATypeVO> list;
        try {
            list = dao.selectAll();
            for (QnATypeVO vo: list) {
                System.out.println(vo);
            }
            System.out.println("성공");
        }
        catch(QnATypeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSelectById() {
        String id = "REQ";
        QnATypeDaoImpl dao = ctx.getBean("qnATypeDao", QnATypeDaoImpl.class);
        QnATypeVO qnATypeVO;
        try {
            qnATypeVO = dao.selectById(id);
            System.out.println(qnATypeVO);
        }
        catch(QnATypeException e) {
            System.out.println(e.getMessage());
        }
    }
}
