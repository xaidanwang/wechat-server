package com.github.aidan.wechat.account;

import com.github.aidan.wechat.account.common.RedisDo;
import com.github.aidan.wechat.account.dao.WechatAccountDoMapper;
import com.github.aidan.wechat.account.util.EmptyUtil;
import com.github.aidan.wechat.account.util.UuidUtils;
import com.github.aidan.wechat.account.vo.AccountVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatAccountApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	private WechatAccountDoMapper wechatAccountDoMapper;

	@Autowired
	private RedisDo redisDo;

	@Test
	public void Test(){

		ListOperations listOperations = redisTemplate.opsForList();

		boolean flag = redisDo.tryLock("lock",1000L);
		System.out.println(redisTemplate.opsForValue().get("lock"));
		System.out.println(flag);
		System.out.println(listOperations.leftPop("test"));
		System.out.println("Redis List Size :"+listOperations.size("test"));

	}



}
