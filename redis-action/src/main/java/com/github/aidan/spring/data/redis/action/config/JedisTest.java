package com.github.aidan.spring.data.redis.action.config;

import redis.clients.jedis.Jedis;

/**
 * @author wang yi fei
 * @date 2019/6/24 20:20
 */
public class JedisTest {

	public static void main(String[] args) {
/*		Jedis jedis = new Jedis ("192.168.9.190",6379);
		jedis.del("codehole");
		Long startTime = System.currentTimeMillis();
		for (int i = 0;i < 100000; i++){
			jedis.pfadd("codehole",String.valueOf(i));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗时： "+ (endTime-startTime));
		long tatal = jedis.pfcount("codehole");
		System.out.println("耗时： "+ (System.currentTimeMillis()-endTime));
		System.out.printf("%d %d \n",100000, tatal);
		jedis.close();*/
		String address = "测试小区／1期／测试楼栋／测试单元／1层";
		System.out.println(address.indexOf("／"));
		int index = address.indexOf("／");
		String areaName = address.substring(0,index);
		System.out.println(areaName);
		System.out.println(address.substring(index+1));

	}
}
