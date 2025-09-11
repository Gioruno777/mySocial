package com.example.demo.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.mapper.AuthMapper;
import com.example.demo.model.pojo.User_my;
import com.example.demo.utils.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public void signUp(SignUpRequest req) {

        if (authMapper.getUserByEmail(req.getEmail()) != null) {
            throw new IllegalStateException("Email 已被使用");
        }
        if (authMapper.getUserByPhone(req.getPhone()) != null) {
            throw new IllegalStateException("Phone 已被使用");
        }

        String hashed = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(10));

        User_my user = new User_my();

        user.setUserName(req.getUserName());
        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setPassword(hashed);

        authMapper.createUser(user);

    }

    public List<User_my> getAllUsers(int page, int size) {
        if (page < 1)
            page = 1;
        if (size < 1)
            size = 10;
        int offset = (page - 1) * size;
        List<User_my> data = authMapper.getAllUsers(size, offset);
        return data;
    }

    public String login(LoginRequest req) {

        User_my user = authMapper.getUserByPhone(req.getPhone());

        if (user == null) {
            throw new IllegalStateException("帳號密碼錯誤");
        }

        if (!BCrypt.checkpw(req.getPassword(), user.getPassword())) {
            throw new IllegalStateException("帳號密碼錯誤");
        }
        String token = jwtUtil.generateToken(user.getUserId());
        return token;
    }

}
