package com.example.demo.service;

import com.example.demo.dto.Rgmif13CreateRequestDTO;
import com.example.demo.dto.Rgmif13QueryReponseDTO;
import com.example.demo.dto.Rgmif13QueryRequestDTO;
import com.example.demo.dto.Rgmif13UpdateRequestDTO;
import com.example.demo.model.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserServiceV2 {
    
    public PageInfo<Rgmif13QueryReponseDTO> listUser(Rgmif13QueryRequestDTO req);
    public void createUser(Rgmif13CreateRequestDTO  req);
    public User getUserDetail(Long userId);
    public void updateUser(Long userId,Rgmif13UpdateRequestDTO req);
    public void deleteUser(Long userId);
    // public void updateCurrentUserName(Long userId, UpdateUserNameRequestDTO req);
    // public void deleteCurrentUserName(Long userId);
}
