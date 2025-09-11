package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.pojo.User_my;

@Mapper
public interface UserMapper {

    public User_my getUserById(@Param("userId") Long userId);

    public void updateUserName(@Param("userId") Long userId, @Param("userName") String userName);

    public void deleteUser(@Param("userId") Long userId);

}
