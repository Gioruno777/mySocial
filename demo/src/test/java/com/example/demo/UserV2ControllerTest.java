package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.UserV2Controller;
import com.example.demo.model.pojo.User;
import com.example.demo.service.UserServiceV2;
import com.example.demo.utils.JwtUtil;
import com.github.pagehelper.PageInfo;

@WebMvcTest(
    controllers = UserV2Controller.class,
    excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
    }
)
@AutoConfigureMockMvc(addFilters = false)
public class UserV2ControllerTest {
   @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserServiceV2 userServiceV2;

    @MockitoBean
    JwtUtil jwtUtil;

    @Test
    void listUser_success() throws Exception {
        when(userServiceV2.listUser(any()))
            .thenReturn(new PageInfo<>());

        mockMvc.perform(post("/userV2/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isOk());
    }

    @Test
    void getUserDetail_success() throws Exception {
        when(userServiceV2.getUserDetail(1L)).thenReturn(new User());
        mockMvc.perform(get("/userV2/1"))
            .andExpect(status().isOk());
    }

    @Test
    void getUserDetail_serviceThrows_returns500() throws Exception {
        when(userServiceV2.getUserDetail(1L))
            .thenThrow(new RuntimeException("boom"));

        mockMvc.perform(get("/userV2/1"))
            .andExpect(status().isInternalServerError());
    }
    
}