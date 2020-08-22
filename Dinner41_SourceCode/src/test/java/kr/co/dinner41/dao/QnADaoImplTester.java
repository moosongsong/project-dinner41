package kr.co.dinner41.dao;

import kr.co.dinner41.exception.QnAException;
import kr.co.dinner41.exception.QnATypeSelectException;
import kr.co.dinner41.vo.QnATypeVO;
import kr.co.dinner41.vo.QnAVO;
import kr.co.dinner41.vo.UserVO;
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
public class QnADaoImplTester {
    @Autowired
    private ApplicationContext ctx;

    @Test
    public void testInsert() throws QnATypeSelectException {
        QnADaoImpl dao = ctx.getBean("qnADao", QnADaoImpl.class);
        QnATypeDaoImpl qnATypeDao = ctx.getBean("qnATypeDao", QnATypeDaoImpl.class);
        QnATypeVO qnATypeVo = qnATypeDao.selectById("REQ");
        UserDaoImpl userDao = ctx.getBean("userDao", UserDaoImpl.class);
        UserVO user = userDao.selectById(4);

        QnAVO qna = new QnAVO(0, qnATypeVo, user,
                null, "Can..I?", "please confirm",
                null, null, null);

        try {
            dao.insert(qna);
            System.out.println("Success");
        }
        catch(QnAException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testUpdate() throws QnATypeSelectException {
        QnADaoImpl dao = ctx.getBean("qnADao", QnADaoImpl.class);
        QnATypeDaoImpl qnATypeDao = ctx.getBean("qnATypeDao", QnATypeDaoImpl.class);
        QnATypeVO qnATypeVo = qnATypeDao.selectById("REQ");
        UserDaoImpl userDao = ctx.getBean("userDao", UserDaoImpl.class);
        UserVO manager = userDao.selectById(1);

        QnAVO qna = new QnAVO(3, qnATypeVo, null,
                manager, null, null,
                null, "please wait", null);
        try {
            dao.update(qna);
            System.out.println("Success");
        } catch (QnAException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll(){
        QnADao dao = ctx.getBean("qnADao", QnADaoImpl.class);
        try {
            List<QnAVO> list = dao.selectAll(1, 2);
            System.out.println("Success");
            for (QnAVO vo: list) {
                System.out.println(vo);
            }
        } catch (QnAException e) {
            e.printStackTrace();
        }

        try {
            List<QnAVO> list = dao.selectAll(1, 2, "QUE");
            System.out.println("Success");
            for (QnAVO vo: list) {
                System.out.println(vo);
            }
        } catch (QnAException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTotalRecord(){
        QnADao dao = ctx.getBean("qnADao", QnADaoImpl.class);
        try {
            int total = dao.getTotalRecord();
            System.out.println("Success : "+total);
        } catch (QnAException e) {
            e.printStackTrace();
        }

        try {
            int total = dao.getTotalRecord("QUE");
            System.out.println("Success : "+total);
        } catch (QnAException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectById(){
        QnADao dao = ctx.getBean("qnADao", QnADaoImpl.class);
        try {
            QnAVO qnAVO = dao.selectById(2);
            System.out.println(qnAVO);
            System.out.println("Success");
        } catch (QnAException e) {
            e.printStackTrace();
        }
    }
}
