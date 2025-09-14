package com.example.demo.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.InsertUserRequestDTO;
import com.example.demo.dto.UpdateUserNameRequestDTO;
import com.example.demo.exception.ConflictErrorException;
import com.example.demo.exception.NotFoundErrorException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.pojo.User_my;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(InsertUserRequestDTO req) {

        if (userMapper.getUserByEmail(req.getEmail()) != null) {
            throw new ConflictErrorException("Email已被使用!");
        }
        if (userMapper.getUserByPhone(req.getPhone()) != null) {
            throw new ConflictErrorException(" Phone已被使用!");
        }

        String hashed = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(10));

        User_my user = new User_my();
        user.setUserName(req.getUserName());
        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setPassword(hashed);

        userMapper.insertUser(user);
    }

    @Override
    @Transactional
    public void updateCurrentUserName(Long userId, UpdateUserNameRequestDTO req) {

        if (userMapper.getUserById(userId) == null) {
            throw new NotFoundErrorException("錯誤");
        }

        userMapper.updateUserName(userId, req.getUserName());
    }

    @Override
    public void deleteCurrentUserName(Long userId) {

        if (userMapper.getUserById(userId) == null) {
            throw new NotFoundErrorException("無此用戶");
        }
        userMapper.deleteUser(userId);
    }
}
