package com.example.demo.mapper;

import com.example.demo.dto.Rgmif13QueryReponseDTO;
import com.example.demo.model.pojo.User;

public class Rgmif13DTOMapper {

    public static Rgmif13QueryReponseDTO toDTO(User user) {
        if (user == null) return null;
        Rgmif13QueryReponseDTO dtoList= new Rgmif13QueryReponseDTO();
        dtoList.setUserId(user.getUserId());
        dtoList.setUserName(user.getUserName());
        dtoList.setEmail(user.getEmail());
        dtoList.setPhone(user.getPhone());
        dtoList.setPassword(maskPassword(user.getPassword()));
        return dtoList;
    }

    private static String maskPassword(String passWord) {
        return "2486";
    }
}
