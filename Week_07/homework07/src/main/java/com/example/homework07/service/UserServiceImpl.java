package com.example.homework07.service;

import com.example.homework07.model.User;
import com.example.homework07.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired(required=false)
    private UserMapper userMapper;

    @Override
    public User queryUser(int id) {
        return userMapper.queryUser(1);
    }
}
