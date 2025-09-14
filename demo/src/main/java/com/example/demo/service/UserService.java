package com.example.demo.service;

import com.example.demo.dto.InsertUserRequestDTO;
import com.example.demo.dto.UpdateUserNameRequestDTO;

public interface UserService {
    public void createUser(InsertUserRequestDTO req);

    public void updateCurrentUserName(Long userId, UpdateUserNameRequestDTO req);

    public void deleteCurrentUserName(Long userId);

}