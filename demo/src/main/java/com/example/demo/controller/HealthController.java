package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.HealthRequest;

@RestController
@RequestMapping("/health")
public class HealthController {

    // http://localhost:8080/health?name=Tom&age=20
    @GetMapping
    public ResponseEntity<?> getParam(
            @RequestParam String name,
            @RequestParam Integer age) {
        Map<String, Object> response = new HashMap<>();
        response.put("name", name);
        response.put("age", age);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/param")
    public ResponseEntity<?> getParam1(
            @RequestParam List<String> name) {
        System.out.println(name);
        return ResponseEntity.ok(name);
    }

    @PostMapping
    public ResponseEntity<?> postParam(
            @RequestBody HealthRequest req) {
        Map<String, Object> response = new HashMap<>();
        response.put("name", req.getName());
        response.put("age", req.getAge());
        // System.out.println(response);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }
}