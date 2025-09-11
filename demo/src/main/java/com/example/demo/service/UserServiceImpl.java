package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserNameRequest;
import com.example.demo.dto.deleteUserRequest;
import com.example.demo.mapper.UserMapper;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void updateCurrentUserName(Long userId, UserNameRequest req) {

        if (userMapper.getUserById(userId) == null) {
            throw new IllegalStateException("錯誤");
        }

        userMapper.updateUserName(userId, req.getUserName());
    }

    @Override

    public void deleteCurrentUserName(deleteUserRequest req) {

        if (userMapper.getUserById(req.getUserId()) == null) {
            throw new IllegalStateException("錯誤");
        }
        userMapper.deleteUser(req.getUserId());
    }
}
