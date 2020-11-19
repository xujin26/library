package com.yuzhou.service.impl;

import com.yuzhou.entity.Book;
import com.yuzhou.entity.Borrow;
import com.yuzhou.repository.BookRepository;
import com.yuzhou.repository.BorrowRepository;
import com.yuzhou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    private final int LIMIT = 6;

    @Override
    public List<Book> findAll(int page) {
        int index = (page-1)*LIMIT;
        return bookRepository.findAll(index,LIMIT);
    }

    @Override
    public int getPages() {
        int count = bookRepository.count();
        int page = 0;
        if(count % LIMIT == 0){
            page = count/LIMIT;
        }else{
            page = count/LIMIT + 1;
        }
        return page;
    }

    @Override
    public void addBorrow(Integer bookid, Integer readerid) {
        //借书时间，字符串
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = simpleDateFormat.format(date);
        //还书时间，借书时间+14天
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;//今天一年中第几天+14
        calendar.set(Calendar.DAY_OF_YEAR, dates);//存起来
        Date date2 = calendar.getTime();
        String returnTime = simpleDateFormat.format(date2);
        borrowRepository.insert(bookid,readerid,borrowTime,returnTime,null,0);
    }

    @Override
    public List<Borrow> findAllBorrowByReaderId(Integer readerid, Integer page) {
        //业务：将page转化成index，limit
        int index = (page-1)*LIMIT;
        return borrowRepository.findAllByReaderId(readerid,index,LIMIT);
    }

    @Override
    public int getBorrowPages(Integer readerid) {
        int count = borrowRepository.count(readerid);
        int page = 0;
        if(count % LIMIT == 0){
            page = count/LIMIT;
        }else{
            page = count/LIMIT + 1;
        }
        return page;
    }

    @Override
    public List<Borrow> findAllBorrowByState(Integer state, Integer page) {
        //业务：将page转化成index，limit
        int index = (page-1)*LIMIT;
        return borrowRepository.findAllByState(state,index,LIMIT);
    }

    @Override
    public int getBorrowPagesByState(Integer state) {
        int count = borrowRepository.countByState(state);
        int page = 0;
        if(count % LIMIT == 0){
            page = count/LIMIT;
        }else{
            page = count/LIMIT + 1;
        }
        return page;
    }

    @Override
    public void handleBorrow(Integer borrowid, Integer state, Integer adminid) {
        borrowRepository.handle(borrowid,state,adminid);
    }
}
