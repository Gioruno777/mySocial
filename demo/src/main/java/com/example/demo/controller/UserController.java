package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserNameRequest;
import com.example.demo.dto.deleteUserRequest;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Long getUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PutMapping("/userName")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserNameRequest req) {

        try {
            userService.updateCurrentUserName(getUserId(), req);
            return ResponseEntity.ok("更新成功");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @DeleteMapping("/userID")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody deleteUserRequest req) {

        try {
            userService.deleteCurrentUserName(req);
            return ResponseEntity.ok("更新成功");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }
}
