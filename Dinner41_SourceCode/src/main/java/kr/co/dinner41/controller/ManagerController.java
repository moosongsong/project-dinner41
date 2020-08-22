package kr.co.dinner41.controller;

import kr.co.dinner41.service.manager.ManagerApproveService;
import kr.co.dinner41.service.manager.ManagerBlockService;
import kr.co.dinner41.service.manager.ManagerDeleteService;
import kr.co.dinner41.service.manager.ManagerRejectService;
import kr.co.dinner41.service.store.StoreViewByUserService;
import kr.co.dinner41.vo.StoreVO;
import kr.co.dinner41.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class ManagerController {
    @Autowired
    @Qualifier("managerApproveService")
    ManagerApproveService approveService;

    @Autowired
    @Qualifier("managerBlockService")
    ManagerBlockService blockService;

    @Autowired
    @Qualifier("managerDeleteService")
    ManagerDeleteService deleteService;

    @Autowired
    @Qualifier("managerRejectService")
    ManagerRejectService rejectService;

    @Autowired
    @Qualifier("storeViewByUserService")
    StoreViewByUserService storeViewByUserService;

    @RequestMapping(value = "/ad/approve/{id}/store", method = RequestMethod.GET)
    public void approve(@PathVariable("id") String id, HttpSession session, HttpServletResponse response, HttpServletRequest request){
        int storeId = Integer.parseInt(id);
        UserVO manager = (UserVO) session.getAttribute("loginUser");

        PrintWriter out;
        if ((manager == null)||(!manager.getType().getId().equals("AD"))){
            try {
                out = response.getWriter();
                out.println("<script>" +
                        "alert('Wrong Access!');" +
                        "location.href='"+request.getContextPath() +"/';"+
                        "</script>");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StoreVO store = storeViewByUserService.execute(storeId);
        approveService.execute(manager, storeId);

        try {
            out = response.getWriter();
            out.println("<script>" +
                    "alert('Approve Success!');" +
                    "location.href='"+request.getContextPath() +"/ad/"+storeId+"/store';"+
                    "</script>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/ad/reject/{id}/store", method = RequestMethod.POST)
    public void reject(@RequestParam("content")String content, @PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        int storeId = Integer.parseInt(id);
        UserVO manager = (UserVO) session.getAttribute("loginUser");

        PrintWriter out;
        if ((manager == null)||(!manager.getType().getId().equals("AD"))){
            try {
                out = response.getWriter();
                out.println("<script>" +
                        "alert('Wrong Access!');" +
                        "location.href='"+request.getContextPath() +"/';"+
                        "</script>");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StoreVO store = storeViewByUserService.execute(storeId);
        rejectService.execute(manager, storeId, content);

        try {
            out = response.getWriter();
            out.println("<script>" +
                    "alert('Reject Success!');" +
                    "location.href='"+request.getContextPath() +"/ad/"+storeId+"/store';"+
                    "</script>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/ad/block/{id}/store", method = RequestMethod.POST)
    public void block(@RequestParam("content")String content, @PathVariable("id") String id, HttpSession session, HttpServletResponse response, HttpServletRequest request){
        int storeId = Integer.parseInt(id);
        UserVO manager = (UserVO) session.getAttribute("loginUser");

        PrintWriter out;
        if ((manager == null)||(!manager.getType().getId().equals("AD"))){
            try {
                out = response.getWriter();
                out.println("<script>" +
                        "alert('Wrong Access!');" +
                        "location.href='"+request.getContextPath() +"/';"+
                        "</script>");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StoreVO store = storeViewByUserService.execute(storeId);
        blockService.execute(manager, storeId, content);

        try {
            out = response.getWriter();
            out.println("<script>" +
                    "alert('Reject Success!');" +
                    "location.href='"+request.getContextPath() +"/ad/"+storeId+"/store';"+
                    "</script>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/ad/delete/{id}/store", method = RequestMethod.POST)
    public void delete(@RequestParam("content")String content, @PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        int storeId = Integer.parseInt(id);
        UserVO manager = (UserVO) session.getAttribute("loginUser");

        PrintWriter out;
        if ((manager == null)||(!manager.getType().getId().equals("AD"))){
            try {
                out = response.getWriter();
                out.println("<script>" +
                        "alert('Wrong Access!');" +
                        "location.href='"+request.getContextPath() +"/';"+
                        "</script>");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StoreVO store = storeViewByUserService.execute(storeId);
        deleteService.execute(manager, storeId, content);

        try {
            out = response.getWriter();
            out.println("<script>" +
                    "alert('Reject Success!');" +
                    "location.href='"+request.getContextPath() +"/ad/"+storeId+"/store';"+
                    "</script>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}