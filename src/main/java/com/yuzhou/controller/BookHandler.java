package com.yuzhou.controller;

import com.yuzhou.entity.Book;
import com.yuzhou.entity.Borrow;
import com.yuzhou.entity.Reader;
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
@RequestMapping("/book")
public class BookHandler {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public void book(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String method = request.getParameter("method");
        if(method == null){
            method = "findAll";
        }
        HttpSession session = request.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        //流程控制
        switch (method){
            case "findAll":
                String pageStr = request.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Book> list = bookService.findAll(page);
                request.setAttribute("list",list);
                request.setAttribute("dataPrePage",6);
                request.setAttribute("currentPage",page);
                request.setAttribute("pages",bookService.getPages());
                request.getRequestDispatcher("index.jsp").forward(request,response);
                break;
            case "addBorrow":
                String bookidStr = request.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                //添加借书请求
                bookService.addBorrow(bookid,reader.getId());
                response.sendRedirect("/book?method=findAllBorrow&page=1");
                break;
            case "findAllBorrow":
                pageStr = request.getParameter("page");
                page = Integer.parseInt(pageStr);
                //展示当前用户的所有借书记录 Model
                List<Borrow> borrowList = bookService.findAllBorrowByReaderId(reader.getId(),page);
                request.setAttribute("list",borrowList);
                request.setAttribute("dataPrePage",6);
                request.setAttribute("currentPage",page);
                request.setAttribute("pages",bookService.getBorrowPages(reader.getId()));
                request.getRequestDispatcher("borrow.jsp").forward(request,response);
                break;
        }
    }
}
