package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.model.pojo.User_my;

public interface AuthService {
    public void signUp(SignUpRequest req);

    public List<User_my> getAllUsers(int page, int size);

    public String login(LoginRequest req);
}
