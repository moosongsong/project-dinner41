package kr.co.dinner41.controller;

import kr.co.dinner41.command.ReviewInsertCommand;
import kr.co.dinner41.service.review.ReviewInsertService;
import kr.co.dinner41.service.review.ReviewListService;
import kr.co.dinner41.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    @Qualifier("reviewInsertService")
    ReviewInsertService insertService;

    @Autowired
    @Qualifier("reviewListService")
    ReviewListService listService;

    @RequestMapping(value = "/gm/{orderId}/review", method = RequestMethod.GET)
    public String insert(HttpSession session, @PathVariable("orderId") String orderId, Model model, HttpServletResponse response, HttpServletRequest request){
        UserVO user = (UserVO) session.getAttribute("loginUser");

        String userType = user.getType().getId();
        if (!userType.equals("GM")){
            return "redirect:/";
        }

        int order_id = Integer.parseInt(orderId);
        StoreVO store = insertService.getStore(order_id);

        if(insertService.isHaveReview(order_id)){
            try {
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script>" +
                        "alert('Review is already exist...');" +
                        "location.href='" + request.getContextPath() + "/gm/"+store.getId()+"/1/review';"+
                        "</script>");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<ReveiwMenuVO> menus = insertService.getMenus(order_id);

        model.addAttribute("store", store);
        model.addAttribute("menus", menus);
        model.addAttribute("address", user.getAddress());
        return "user/reviewInsert";
    }

    @RequestMapping(value = "/gm/{orderId}/review", method = RequestMethod.POST)
    public String insert(ReviewInsertCommand command, HttpSession session, @PathVariable("orderId") String orderId){
        UserVO user = (UserVO) session.getAttribute("loginUser");
        int int_order_id = Integer.parseInt(orderId);
        insertService.execute(command, user, int_order_id);
        StoreVO store = insertService.getStore(int_order_id);
        return "redirect:/gm/"+store.getId()+"/1/review";
    }

    @RequestMapping(value = "/gm/{storeId}/{page}/review", method = RequestMethod.GET)
    public String list(@PathVariable("page") String page, Model model,
                       HttpSession session, @PathVariable("storeId") String storeId){
        String view_name = "redirect:/";
        int int_page = Integer.parseInt(page);
        int int_storeId = Integer.parseInt(storeId);
        List<ReviewVO> list;
        List<PageVO> pageList;

        UserVO user = (UserVO) session.getAttribute("loginUser");
        if (user == null){
            return view_name;
        }

        String userType = user.getType().getId();
        if (!userType.equals("GM")){
            return view_name;
        }

        list = listService.execute(int_storeId, int_page);
        pageList = listService.getPages(int_page, int_storeId);
        StoreVO store = listService.getStore(int_storeId);
        double avg = listService.getTotalScore(int_storeId);

        model.addAttribute("list", list);
        model.addAttribute("pages", pageList);
        model.addAttribute("page", int_page);
        model.addAttribute("storeId", int_storeId);
        model.addAttribute("store", store);
        model.addAttribute("avg", avg);
        model.addAttribute("address", user.getAddress());

        return "user/reviewList";
    }

}
