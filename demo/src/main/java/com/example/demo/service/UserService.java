package com.example.demo.service;

import com.example.demo.dto.InsertUserRequestDTO;
import com.example.demo.dto.Rgmif13QueryReponseDTO;
import com.example.demo.dto.Rgmif13QueryRequestDTO;
import com.example.demo.dto.UpdateUserNameRequestDTO;
import com.github.pagehelper.PageInfo;

public interface UserService {

    public PageInfo<Rgmif13QueryReponseDTO> listUser(Rgmif13QueryRequestDTO req);

    public void createUser(InsertUserRequestDTO req);

    public void updateCurrentUserName(Long userId, UpdateUserNameRequestDTO req);

    public void deleteCurrentUserName(Long userId);

}