package com.example.mini.service.impl;

import com.example.mini.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String create(String username) {
        return username;
    }
}
