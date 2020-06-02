package com.github.aidan.spring.data.redis.action.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

/**
 * @author wang yi fei
 * @date 2019/6/23 11:40
 */
public class RedisDelayQueue<T> {
	static class TaskItem<T>{
		public String id;
		public T msg;
	}

	private Jedis jedis;
	private String queueKey;

	private TypeReference<TaskItem<T>> taskType = new TypeReference<TaskItem<T>>(){};

	public RedisDelayQueue(Jedis jedis, String queueKey) {
		this.jedis = jedis;
		this.queueKey = queueKey;
	}

	public void delay(T msg){
		TaskItem<T> task = new TaskItem<>();
		//  分配唯一UUID
		task.id = UUID.randomUUID().toString();
		task.msg = msg;
		//  fastjson 序列化
		String s = JSON.toJSONString(task);
		//  塞入延时队列，5s 后再试
		jedis.zadd(queueKey,System.currentTimeMillis()+5000,s);

	}

	public void loop(){
		while (!Thread.interrupted()){
			//  只取一条
			Set<String> values = jedis.zrangeByScore(queueKey,0,System.currentTimeMillis(),0,1);
			if (values.isEmpty()){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					break;
				}
				continue;
			}
			String s = values.iterator().next();
			if (jedis.zrem(queueKey,s) > 0){    //  抢到了
				//  fastjson 反序列化
				TaskItem<T> task = JSON.parseObject(s,taskType);
				this.handleMsg(task.msg);

			}
		}
	}

	public void handleMsg(T msg){
		System.out.println(msg);
	}

/*	public static void main(String[] args) {
		Jedis  jedis = new Jedis ("192.168.9.190",6379);
		RedisDelayQueue<String> queue = new RedisDelayQueue<>(jedis,"q-demo");
		Thread producer = new Thread(){
			public void run() {
				for (int  i =0; i < 10 ; i++){
					queue.delay("codehole"+ i);
				}
			}
		};

		Thread consumer = new Thread(){

			public void run() {
				queue.loop();
			}
		};
		producer.start();
		consumer.start();
		try {
			producer.join();
			Thread.sleep(6000L);
			consumer.interrupt();
			consumer.join();
		}catch (InterruptedException e){

		}
	}*/
}
