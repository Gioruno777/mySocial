package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Rgmif13CreateRequestDTO;
import com.example.demo.dto.Rgmif13UpdateRequestDTO;
import com.example.demo.dto.Rgmif13QueryReponseDTO;
import com.example.demo.dto.Rgmif13QueryRequestDTO;
import com.example.demo.model.pojo.User;
import com.example.demo.service.UserServiceV2;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userV2")
@Slf4j
public class UserV2Controller {
    
    @Autowired
    private UserServiceV2 userServiceV2;

    // private Long getUserId() {
    //     return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    // }
    
    @PostMapping("/list")
    public ResponseEntity<PageInfo<Rgmif13QueryReponseDTO>> listUser(@RequestBody Rgmif13QueryRequestDTO req ){
        log.info("UserRequest,page={}", req);
        return ResponseEntity.ok(userServiceV2.listUser(req));
    };

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetail(@PathVariable Long userId){
        try{
            User user = userServiceV2.getUserDetail(userId);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系統發生錯誤"+e);
        }
    }    

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Rgmif13CreateRequestDTO  req) {
        log.info("CreateRequest={}", req);
        try{
            userServiceV2.createUser(req);
            return ResponseEntity.ok("新增成功");
        }catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系統發生錯誤");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody Rgmif13UpdateRequestDTO req){
        try{
            userServiceV2.updateUser(userId,req);
            return ResponseEntity.ok("更新成功");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        try{
            userServiceV2.deleteUser(userId);
            return ResponseEntity.ok("刪除成功");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
