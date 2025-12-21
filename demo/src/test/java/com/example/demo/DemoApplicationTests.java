package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.UserServiceV2;
import com.github.pagehelper.PageInfo;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserServiceV2 userServiceV2;


	// public void testListUser() {
	// 	List<User_my> userList = userMapper.list();
	// 	System.out.println("size=" + (userList == null ? "null" : userList.size()));
	// 	assertNotNull(userList, "Mapper 回傳 null，可能沒注入成功");
	// 	userList.stream().forEach(user -> {
	// 		System.out.println(user);
	// 	});

	// }

}
