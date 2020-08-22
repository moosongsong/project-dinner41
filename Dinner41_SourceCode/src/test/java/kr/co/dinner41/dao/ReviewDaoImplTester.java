package kr.co.dinner41.dao;

import kr.co.dinner41.exception.ReviewException;
import kr.co.dinner41.exception.ReviewInsertException;
import kr.co.dinner41.exception.ReviewSelectException;
import kr.co.dinner41.vo.ReviewVO;
import kr.co.dinner41.vo.StoreVO;
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
public class ReviewDaoImplTester {
    @Autowired
    private ApplicationContext ctx;

    @Test
    public void testInsert(){
        ReviewDaoImpl dao = ctx.getBean("reviewDao", ReviewDaoImpl.class);
        UserDaoImpl userDao = ctx.getBean("userDao", UserDaoImpl.class);
        UserVO user = userDao.selectById(3);
        StoreDaoImpl storeDao = ctx.getBean("storeDao", StoreDaoImpl.class);
        StoreVO store = storeDao.selectById(1);

        ReviewVO reviewVO = new ReviewVO(0, store, user, "taste Good", 4,null,0);

        try {
            dao.insert(reviewVO);
        } catch (ReviewInsertException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectById(){
        ReviewDaoImpl dao = ctx.getBean("reviewDao", ReviewDaoImpl.class);
        try {
            ReviewVO reviewVO = dao.selectedById(1);
            System.out.println(reviewVO);
            System.out.println("Success");
        } catch (ReviewSelectException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectByStoreId(){
        ReviewDaoImpl dao = ctx.getBean("reviewDao", ReviewDaoImpl.class);
        try {
            List<ReviewVO> list = dao.selectedByStoreId(1, 2, 1);
            for (ReviewVO vo:list) {
                System.out.println(vo);
            }
            System.out.println("Success");
        } catch (ReviewSelectException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectedAll(){
        ReviewDaoImpl dao = ctx.getBean("reviewDao", ReviewDaoImpl.class);
        try {
            List<ReviewVO> list = dao.selectedAll();
            for (ReviewVO vo:list) {
                System.out.println(vo);
            }
            System.out.println("Success");
        } catch (ReviewSelectException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTotalRecord(){
        ReviewDaoImpl dao = ctx.getBean("reviewDao", ReviewDaoImpl.class);
        try {
            int total = dao.getTotalRecordByStoreId(1);
            System.out.println("Success : "+total);
        } catch (ReviewException e) {
            e.printStackTrace();
        }
    }
}
