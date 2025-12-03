package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Rgmif13ConditionsDTO;
import com.example.demo.model.pojo.User;

@Mapper
public interface UserMapperV2 { 
    List<User> listUsers(Rgmif13ConditionsDTO conditions);
    User findByPhone(@Param("phone") String phone);
    User findByUserId(@Param("userId") Long userId);
    public int insertUser(User user);
    public int updateUser(User user);

    public int deleteUser(@Param("userId") Long userId);
}
