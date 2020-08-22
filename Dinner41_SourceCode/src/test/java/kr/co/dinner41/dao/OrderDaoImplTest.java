//package kr.co.dinner41.dao;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import kr.co.dinner41.vo.OrderViewVO;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations= {
//        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
//        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
//})
//public class OrderDaoImplTest {
//
//    @Autowired
//    private ApplicationContext ctx;
//
//    @Test
//    public void list() {
//    	OrderDao dao = ctx.getBean("orderDao", OrderDaoImpl.class);
//    	List<OrderViewVO> list = dao.selectAllOrderView(16);
//
//    	for (OrderViewVO vo : list) {
//    		System.out.println(vo.getOrder_id());
//    		System.out.println(vo.getOrder_reserve_date());
//    		System.out.println(vo.getStoreName());
//    		System.out.println(vo.getPrice());
//    	}
//    }
//}
