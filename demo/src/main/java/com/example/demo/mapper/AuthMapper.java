package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.pojo.User_my;

@Mapper
public interface AuthMapper {

    public List<User_my> list();

    public User_my getUserByEmail(String email);

    public User_my getUserByPhone(String phone);

    public int createUser(User_my user);

    public List<User_my> getAllUsers(@Param("size") int size, @Param("offset") int offset);

}
