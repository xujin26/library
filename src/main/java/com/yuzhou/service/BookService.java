package com.yuzhou.service;

import com.yuzhou.entity.Book;
import com.yuzhou.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public int getPages();
    public void addBorrow(Integer bookid,Integer readerid);
    public List<Borrow> findAllBorrowByReaderId(Integer readerid, Integer page);
    public int getBorrowPages(Integer readerid);
    public List<Borrow> findAllBorrowByState(Integer state,Integer page);
    public int getBorrowPagesByState(Integer state);
    public void handleBorrow(Integer borrowid,Integer state,Integer adminid);
}
