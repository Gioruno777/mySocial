package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.convert.Rgmif13Convert;
import com.example.demo.dao.UserMapper;
import com.example.demo.dao.UserMapperV2;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.Rgmif13ConditionsDTO;
import com.example.demo.dto.Rgmif13CreateRequestDTO;
import com.example.demo.dto.Rgmif13QueryReponseDTO;
import com.example.demo.dto.Rgmif13QueryRequestDTO;
import com.example.demo.dto.Rgmif13UpdateRequestDTO;
import com.example.demo.dto.UpdateUserNameRequestDTO;
import com.example.demo.exception.ConflictErrorException;
import com.example.demo.exception.InsertFailedException;
import com.example.demo.exception.NotFoundErrorException;
import com.example.demo.model.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceV2impl  implements UserServiceV2 {
    @Autowired
    private UserMapperV2 userMapperV2;

    @Override
    public PageInfo<Rgmif13QueryReponseDTO> listUser(Rgmif13QueryRequestDTO req){
        Rgmif13ConditionsDTO  conditions = req.getDataObj();
        PaginationDTO page = req.getPagination();
        PageHelper.startPage(page.getCurrent_page(), page.getPage_size());
        List<User> list = userMapperV2.listUsers(conditions);
        List<Rgmif13QueryReponseDTO> dtoList = list.stream().map(Rgmif13Convert::toDTO).toList();   
        return new PageInfo<>(dtoList);
    }

    public User getUserDetail(Long userId){
        User user = userMapperV2.findByUserId(userId);
        if (user == null){
            throw new NoSuchElementException("查無資料");
        }
        return user;
    }
    
    @Override
    public void createUser(Rgmif13CreateRequestDTO  req) {
        User user =  userMapperV2.findByPhone(req.getPhone());
        if (user != null){
            throw new IllegalStateException("User已存在");
        }

        User entity = Rgmif13Convert.toEntityForCreate(req);
        
        int rows = userMapperV2.insertUser(entity);
        if (rows != 1) {
            throw new IllegalStateException("新增失敗，影響筆數非 1 筆");
        }
    }

    @Override
    public void updateUser(Long userId,Rgmif13UpdateRequestDTO req){
        
        User user = userMapperV2.findByUserId(userId);
        if (user == null) {
            throw new NoSuchElementException("User不存在");
        }

        User entity = Rgmif13Convert.toEntityForUpdate(userId,req);
        int rows = userMapperV2.updateUser(entity);
        if (rows != 1) {
            throw new IllegalStateException("更新失敗");
        }

    }
    
    @Override
    public void deleteUser(Long userId){
        User user = userMapperV2.findByUserId(userId);
        if (user == null) {
            throw new NoSuchElementException("User不存在");
        }
        int rows = userMapperV2.deleteUser(userId);
        if (rows != 1) {
            throw new IllegalStateException("刪除失敗");
        }
     }

}
