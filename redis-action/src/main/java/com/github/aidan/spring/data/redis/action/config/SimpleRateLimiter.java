package com.github.aidan.spring.data.redis.action.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * @author wang yi fei
 * @date 2019/6/29 20:09
 */
public class SimpleRateLimiter {
	private Jedis jedis;

	private RedisTemplate redisTemplate;
	public SimpleRateLimiter(Jedis jedis) {
		this.jedis = jedis;
	}
	public boolean isActionAllowed(String userId,String actionKey,int period,int maxCount){
		String key = String.format("hist:%s:%s",userId,actionKey);
		long nowTs = System.currentTimeMillis();
		Pipeline pipe = jedis.pipelined();
		pipe.multi();
		pipe.zadd(key,nowTs,""+nowTs);
		pipe.zremrangeByScore(key,0,nowTs - period* 1000);
		Response<Long> count = pipe.zcard(key);
		pipe.expire(key,period + 1);
		pipe.exec();
		pipe.close();
		return count.get() <= maxCount;
	}
/*	public static void main(String[] args) {
*//*		Jedis  jedis = new Jedis ("192.168.9.190",6379);
		SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
		for (int i = 0; i<20;i++){
			System.out.println(limiter.isActionAllowed("laoqian","replay",60,5));
		}

		String str = "123456789";
		int x = str.indexOf("a");
		System.out.println(x);
		System.out.println(str.substring(x));
		System.out.println(str.substring(x+1));*//*
		RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
		configuration.master("mymaster");
		configuration.sentinel(new RedisNode("192.168.9.173",26379));
		configuration.sentinel(new RedisNode("192.168.9.178",26379));
		configuration.sentinel(new RedisNode("192.168.9.188",26379));
		RedisConnectionFactory connectionFactory = new LettuceConnectionFactory(configuration);
		boolean fag = connectionFactory.getSentinelConnection().isOpen();
		System.out.println(fag);
	}*/
}
