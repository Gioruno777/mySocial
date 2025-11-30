package com.example.demo.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.dto.InsertUserRequestDTO;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.Rgmif13ConditionsDTO;
import com.example.demo.dto.Rgmif13QueryReponseDTO;
import com.example.demo.dto.Rgmif13QueryRequestDTO;
import com.example.demo.dto.UpdateUserNameRequestDTO;
import com.example.demo.exception.ConflictErrorException;
import com.example.demo.exception.NotFoundErrorException;
import com.example.demo.mapper.Rgmif13DTOMapper;
import com.example.demo.model.pojo.User;
import com.example.demo.model.pojo.User_my;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Rgmif13QueryReponseDTO> listUser(Rgmif13QueryRequestDTO req){
        Rgmif13ConditionsDTO  conditions = req.getDataObj();
        PaginationDTO page = req.getPagination();
        PageHelper.startPage(page.getCurrent_page(), page.getPage_size());
        List<User> list = userMapper.listUsers(conditions);
        List<Rgmif13QueryReponseDTO> dtoList = list.stream().map(Rgmif13DTOMapper::toDTO).toList();   
        return new PageInfo<>(dtoList);
    }

    @Override
    public void createUser(InsertUserRequestDTO req) {

        if (userMapper.existsByEmail(req.getEmail())) {
            throw new ConflictErrorException("Email已被使用!!!!");
        }
        
        if (userMapper.existsByEmail(null)){
            throw new ConflictErrorException("Phone已被使用!!!!");
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
