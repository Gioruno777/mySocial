package com.example.demo.model.pojo;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String userName;
    private String phone;
    private String email;
    private String password; 
}
