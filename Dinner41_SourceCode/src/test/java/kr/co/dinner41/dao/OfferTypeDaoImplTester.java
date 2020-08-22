package kr.co.dinner41.dao;

import kr.co.dinner41.exception.QnATypeException;
import kr.co.dinner41.exception.menu.OfferTypeException;
import kr.co.dinner41.vo.OfferTypeVO;
import kr.co.dinner41.vo.QnATypeVO;

import org.junit.Ignore;
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
public class OfferTypeDaoImplTester {
    @Autowired
    private ApplicationContext ctx;

  
    @Ignore
    @Test
    public void testSelectAll() {
        OfferTypeDaoImpl dao = ctx.getBean("offerTypeDao", OfferTypeDaoImpl.class);
        List<OfferTypeVO> list;
        try {
            list = dao.selectAll();
            for (OfferTypeVO vo: list) {
                System.out.println(vo);
            }
            System.out.println("성공");
        }
        catch(OfferTypeException e) {
            System.out.println(e.getMessage());
        }
    }

 

    @Test
    public void testSelectById() {
        String id = "PAC";
        OfferTypeDaoImpl dao = ctx.getBean("offerTypeDao", OfferTypeDaoImpl.class);
        OfferTypeVO offerTypeVO;
        try {
            offerTypeVO = dao.selectById(id);
            System.out.println(offerTypeVO);
        }
        catch(OfferTypeException e) {
            System.out.println(e.getMessage());
        }
    }
}
