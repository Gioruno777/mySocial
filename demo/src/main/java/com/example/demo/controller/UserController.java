package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InsertUserRequestDTO;
import com.example.demo.dto.Rgmif13QueryReponseDTO;
import com.example.demo.dto.Rgmif13QueryRequestDTO;
import com.example.demo.dto.UpdateUserNameRequestDTO;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    private Long getUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    @PostMapping("/list")
    public ResponseEntity<PageInfo<Rgmif13QueryReponseDTO>> listUser(@RequestBody Rgmif13QueryRequestDTO req ){
        log.info("UserRequest,page={}", req);
        return ResponseEntity.ok(userService.listUser(req));
    };


    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@Validated @RequestBody InsertUserRequestDTO req) {
        log.info("UserRequest={}", req);
        userService.createUser(req);
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        response.put("message", "註冊成功");
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> updateUser(@Valid @RequestBody UpdateUserNameRequestDTO req) {
        userService.updateCurrentUserName(getUserId(), req);
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        response.put("success", "更新成功");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userID) {
        userService.deleteCurrentUserName(userID);
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        response.put("success", "刪除成功");
        return ResponseEntity.ok(response);
    }
}
