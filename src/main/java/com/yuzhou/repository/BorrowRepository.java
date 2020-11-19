package com.yuzhou.repository;

import com.yuzhou.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    public void insert(Integer bookid,Integer readerid,String borrowtime,String returntime,Integer adminid,Integer state);
    public List<Borrow> findAllByReaderId(Integer readerid, Integer index, Integer limit);
    public int count(Integer readerid);
    public List<Borrow> findAllByState(Integer state,Integer index,Integer limit);
    public int countByState(Integer state);
    public void handle(Integer borrowid,Integer state,Integer adminid);
}
