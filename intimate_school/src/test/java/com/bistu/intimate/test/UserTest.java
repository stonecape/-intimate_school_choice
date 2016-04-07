package com.bistu.intimate.test;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bistu.intimate.dto.User;
import com.bistu.intimate.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:conf/*.xml"})
public class UserTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setRealName("陈浩彦");
		user.setUserName("chy");
		user.setYn((byte) 1);
		System.out.println("=======B" + ToStringBuilder.reflectionToString(user));
		System.out.println(ToStringBuilder.reflectionToString(userService.addNewUser(user)));
		System.out.println("=======E");
	}
	
	
}
