package com.yuzhou.repository;

import com.yuzhou.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username, String password);
}
