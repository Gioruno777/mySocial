package com.example.demo.service;

import com.example.demo.dto.UserNameRequest;
import com.example.demo.dto.deleteUserRequest;

public interface UserService {
    public void updateCurrentUserName(Long userId, UserNameRequest req);

    public void deleteCurrentUserName(deleteUserRequest req);

}