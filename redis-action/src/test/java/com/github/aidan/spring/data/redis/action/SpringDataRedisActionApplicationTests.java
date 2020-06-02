package com.github.aidan.spring.data.redis.action;

import com.github.aidan.spring.data.redis.action.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataRedisActionApplicationTests {
	static Logger logger = LoggerFactory.getLogger(SpringDataRedisActionApplicationTests.class) ;
	@Test
	public void contextLoads() {
	}
	@Autowired
	private StringRedisTemplate stringRedisTemplate ;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void redisConnectionTest(){
		logger.info("redis连接工厂：{}",stringRedisTemplate.getConnectionFactory());
/*		boolean a = stringRedisTemplate.getConnectionFactory().getSentinelConnection().isOpen();
		System.out.println(a);
		System.out.println(stringRedisTemplate.getConnectionFactory().getSentinelConnection().masters());*/
		//添加key
		stringRedisTemplate.opsForValue().set("user","张三");
		//获取key
		logger.info("从redis中获取key=user的值为：{}",stringRedisTemplate.opsForValue().get("user"));

		//删除key
		stringRedisTemplate.delete("user");

		Address address = new Address("111111111111111","564156456");
		System.out.println(address);
		redisTemplate.opsForHash().put("user","address",address);


		Address address1 = ((Address) redisTemplate.opsForHash().get("user", "address"));
		System.out.println(address1);

		Properties properties = redisTemplate.getConnectionFactory().getConnection().getConfig("lettuce");

		System.out.println(properties.get("pool"));
	}



}
