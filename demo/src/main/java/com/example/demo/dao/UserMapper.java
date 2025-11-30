package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Rgmif13ConditionsDTO;
import com.example.demo.model.pojo.User;
import com.example.demo.model.pojo.User_my;

@Mapper
public interface UserMapper {

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    public User_my getUserById(@Param("userId") Long userId);

    List<User> listUsers(Rgmif13ConditionsDTO conditions);

    public int insertUser(User_my user);

    public void updateUserName(@Param("userId") Long userId, @Param("userName") String userName);

    public void deleteUser(@Param("userId") Long userId);

}
