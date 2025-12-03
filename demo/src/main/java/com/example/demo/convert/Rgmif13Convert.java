package com.example.demo.convert;

import org.mindrot.jbcrypt.BCrypt;

import com.example.demo.dto.Rgmif13CreateRequestDTO;
import com.example.demo.dto.Rgmif13QueryReponseDTO;
import com.example.demo.dto.Rgmif13UpdateRequestDTO;
import com.example.demo.model.pojo.User;

public class Rgmif13Convert {

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
    public static User toEntityForCreate(Rgmif13CreateRequestDTO req) {
        User user= new User();
        user.setUserName(req.getUserName());
        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setPassword(hash(req.getPassword()));;
        return user;
    }

    public static User toEntityForUpdate(Long userID, Rgmif13UpdateRequestDTO req) {
        User user= new User();
        user.setUserId(userID);
        user.setUserName(req.getUserName());
        return user;
    }

    private static String maskPassword(String passWord) {
        return "2486";
    }
    private static String hash(String passWord) {
        String hashed = BCrypt.hashpw(passWord, BCrypt.gensalt(10));
        return hashed;
    }
}
