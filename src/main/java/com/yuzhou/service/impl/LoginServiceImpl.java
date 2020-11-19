package com.yuzhou.service.impl;

import com.yuzhou.repository.AdminRepository;
import com.yuzhou.repository.ReaderRepository;
import com.yuzhou.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private AdminRepository adminRepository;

    public LoginServiceImpl() {
    }

    @Override
    public Object login(String username, String password, String type) {
        Object object = null;
        switch (type){
            case "reader":
                object = readerRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username,password);
                break;
        }
        return object;
    }
}
