package com.yuzhou.controller;

import com.yuzhou.entity.Admin;
import com.yuzhou.entity.Borrow;
import com.yuzhou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHandler {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method == null){
            method = "findAllBorrow";
        }
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        switch (method){
            case "findAllBorrow":
                String pageStr = request.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Borrow> list = bookService.findAllBorrowByState(0,page);
                request.setAttribute("list",list);
                request.setAttribute("dataPrePage",6);
                request.setAttribute("currentPage",page);
                request.setAttribute("pages",bookService.getBorrowPagesByState(0));
                request.getRequestDispatcher("admin.jsp").forward(request,response);
                break;
            case "handle":
                String idStr = request.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                String stateStr = request.getParameter("state");
                Integer state = Integer.parseInt(stateStr);
                bookService.handleBorrow(id,state,admin.getId());
                if(state == 1 || state == 2){
                    response.sendRedirect("/admin?page=1");
                }else{
                    response.sendRedirect("/admin?method=getBorrowed&page=1");
                }
                break;
            case "getBorrowed":
                pageStr = request.getParameter("page");
                page = Integer.parseInt(pageStr);
                list = bookService.findAllBorrowByState(1,page);
                request.setAttribute("list",list);
                request.setAttribute("dataPrePage",6);
                request.setAttribute("currentPage",page);
                request.setAttribute("pages",bookService.getBorrowPagesByState(1));
                request.getRequestDispatcher("return.jsp").forward(request,response);
                break;
        }
    }
}
