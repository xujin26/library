package com.yuzhou.repository;

import com.yuzhou.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index, int limit);
    public int count();
}
