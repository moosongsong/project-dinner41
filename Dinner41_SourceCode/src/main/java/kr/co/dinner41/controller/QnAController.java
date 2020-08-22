package kr.co.dinner41.controller;

import kr.co.dinner41.command.QnAAnswerCommand;
import kr.co.dinner41.command.QnAInsertCommand;
import kr.co.dinner41.service.qna.QnAAnswerService;
import kr.co.dinner41.service.qna.QnAInsertService;
import kr.co.dinner41.service.qna.QnAListService;
import kr.co.dinner41.service.qna.QnAViewService;
import kr.co.dinner41.vo.PageVO;
import kr.co.dinner41.vo.QnAVO;
import kr.co.dinner41.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class QnAController {
    @Autowired
    @Qualifier("qnAInsertService")
    QnAInsertService insertService;

    @Autowired
    @Qualifier("qnAViewService")
    QnAViewService viewService;

    @Autowired
    @Qualifier("qnAListService")
    QnAListService listService;

    @Autowired
    @Qualifier("qnAAnswerService")
    QnAAnswerService answerService;

    @RequestMapping(value = "/qna", method = RequestMethod.GET)
    public String insert(HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("loginUser");

        switch (user.getType().getId()) {
            case "AD":
                return "redirect:/ALL/1/qna";
            case "GM":
                return "user/qnaWrite";
            case "SM":
                return "store/qnaWrite";
            default:
                return "redirect:/";
        }
    }

    @RequestMapping(value = "/qna", method = RequestMethod.POST)
    public void insert(QnAInsertCommand command, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        UserVO user = (UserVO) session.getAttribute("loginUser");
        int id = insertService.execute(command, user);
        response.setCharacterEncoding("utf-8");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PrintWriter out;
        try {
            out = response.getWriter();
            out.println("<script>" +
                    "alert('Register Success!');" +
                    "location.href='"+request.getContextPath() +"/"+id+"/qna';"+
                    "</script>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{type}/{page}/qna", method = RequestMethod.GET)
    public String list(@PathVariable("type") String type, @PathVariable("page") String page
            , HttpSession session, Model model) {
        String view_name = "redirect:/";
        int int_page = Integer.parseInt(page);
        List<QnAVO> list = null;
        List<PageVO> pageList = null;

        UserVO user = (UserVO) session.getAttribute("loginUser");

        switch (user.getType().getId()) {
            case "AD":
                list = listService.execute(type, int_page);
                pageList = listService.getPages(int_page, type);
                view_name = "manage/qnaList";
                break;
            case "GM":
                list = listService.execute(user, type, int_page);
                pageList = listService.getPages(int_page, type, user);
                view_name = "user/qnaList";
                break;
            case "SM":
                list = listService.execute(user, type, int_page);
                pageList = listService.getPages(int_page, type, user);
                view_name = "store/qnaList";
                break;
        }

        model.addAttribute("list", list);
        model.addAttribute("pages", pageList);
        model.addAttribute("type", type);
        model.addAttribute("page", int_page);
        model.addAttribute("address", user.getAddress());
        return view_name;
    }

    @RequestMapping(value = "/{id}/qna", method = RequestMethod.GET)
    public String view(@PathVariable("id") String qnaId, HttpSession session, Model model) {
        int id = Integer.parseInt(qnaId);
        UserVO user = (UserVO) session.getAttribute("loginUser");

        QnAVO qna = viewService.execute(id);
        model.addAttribute("qna", qna);
        model.addAttribute("address", user.getAddress());
        switch (user.getType().getId()) {
            case "AD":
                return "manage/qnaView";
            case "SM":
                return "store/qnaView";
            case "GM":
                return "user/qnaView";
            default:
                return "redirect:/ALL/1/qna";
        }
    }

    @RequestMapping(value = "/{id}/qna", method = RequestMethod.POST)
    public String view(@PathVariable("id") String qnaId, QnAAnswerCommand command, HttpSession session) {
        int id = Integer.parseInt(qnaId);

        UserVO manager = (UserVO) session.getAttribute("loginUser");
        answerService.execute(manager, id, command);

        return "redirect:/" + qnaId + "/qna";
    }
}
