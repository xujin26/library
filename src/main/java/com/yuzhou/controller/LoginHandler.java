package com.yuzhou.controller;

import com.yuzhou.entity.Admin;
import com.yuzhou.entity.Reader;
import com.yuzhou.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller//返模型和视图
@RequestMapping("/login")
public class LoginHandler {
    @Autowired
    private LoginService loginService;

    @PostMapping("")
//    @PostMapping("/index/{username}/{password}/{type}")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        Object object = loginService.login(username,password,type);
        if(object != null){
            HttpSession session = request.getSession();
            switch (type){
                case "reader":
                    Reader reader = (Reader) object;
                    session.setAttribute("reader",reader);
                    System.out.println(reader);
                    //跳转到读者首页
                    response.sendRedirect("/book?page=1");//推荐用重定向
                    break;
                case "admin":
                    Admin admin = (Admin) object;
                    session.setAttribute("admin",admin);
                    System.out.println(admin);
                    response.sendRedirect("/admin?page=1");
                    break;
            }
        }else{
            response.sendRedirect("login.jsp");
        }
    }
}
