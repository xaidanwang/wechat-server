package com.github.aidan.mybatis.demo;

import com.github.aidan.mybatis.demo.dao.DispatchingTaskDoMapper;
import com.github.aidan.mybatis.demo.dto.DispatchingTaskDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private DispatchingTaskDoMapper taskDoMapper;

	@Test
	public void testTaskDTOList(){

		List<DispatchingTaskDTO> taskDTOList = taskDoMapper.getDispatchingTaskDTOList();
		System.out.println("===================================");
		System.out.println(taskDTOList.toString());
		DispatchingTaskDTO dispatchingTaskDTO = new DispatchingTaskDTO();

	}

}

