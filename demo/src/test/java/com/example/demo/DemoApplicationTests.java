package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.AuthMapper;
import com.example.demo.model.pojo.User_my;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private AuthMapper userMapper;

	@Test
	public void testListUser() {
		List<User_my> userList = userMapper.list();
		System.out.println("size=" + (userList == null ? "null" : userList.size()));
		assertNotNull(userList, "Mapper 回傳 null，可能沒注入成功");
		userList.stream().forEach(user -> {
			System.out.println(user);
		});

	}

}
