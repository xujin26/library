package com.yuzhou.repository;

import com.yuzhou.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
