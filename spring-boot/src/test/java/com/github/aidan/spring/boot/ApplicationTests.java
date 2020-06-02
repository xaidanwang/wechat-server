package com.github.aidan.spring.boot;

import com.github.aidan.spring.boot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void toStringTest(){

		User user = new User();
		user.setAge(1);
		user.setName("12321312");

		System.out.println(user);
		log.info("对象格式设置：[{}]",user);
	}

}
