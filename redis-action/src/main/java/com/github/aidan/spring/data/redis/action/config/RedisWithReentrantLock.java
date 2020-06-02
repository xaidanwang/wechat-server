package com.github.aidan.spring.data.redis.action.config;

/**
 * @author wang yi fei
 * @date 2019/6/23 10:47
 */

import org.omg.PortableInterceptor.INACTIVE;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * redis 重入锁
 */
public class RedisWithReentrantLock {
	private ThreadLocal<Map<String,Integer>> lockers =  new ThreadLocal<>();
	private Jedis jedis;

	public RedisWithReentrantLock(Jedis jedis) {
		this.jedis = jedis;
	}
	private boolean _lock(String key){

		return jedis.setex(key,5,"") != null;
	}
	private void _unlock(String key){
		jedis.del(key);
	}
	private Map<String,Integer> currentLockers(){
		Map<String,Integer> refs =  lockers.get();
		if (refs != null){
			return refs;
		}
		lockers.set(new HashMap<>());
		return lockers.get();
	}
	public boolean lock(String key){
		Map<String,Integer> refs = currentLockers();
		Integer refCnt = refs.get(key);
		if (refCnt != null){
			refs.put(key,refCnt+1);
			return true;
		}
		boolean ok = this._lock(key);
		if (!ok){
			return false;
		}
		refs.put(key,1);
		return true;
	}

	public boolean unlock(String key){
		Map<String,Integer> refs =currentLockers();
		Integer refCnt = refs.get(key);
		if (refCnt == null){
			return false;
		}
		refCnt -= 1;
		if (refCnt > 0){
			refs.put(key,refCnt);
		}else {
			refs.remove(key);
			this._unlock(key);
		}
		return true;
	}

/*	public static void main(String[] args) {

		Jedis  jedis = new Jedis ("192.168.9.190",6379);
		RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis);
		System.out.println(redis.lock("codehole"));
		System.out.println(redis.lock("codehole"));
		System.out.println(redis.unlock("codehole"));
		System.out.println(redis.unlock("codehole"));

	}*/
}
