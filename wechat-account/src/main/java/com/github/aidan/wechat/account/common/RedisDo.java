package com.github.aidan.wechat.account.common;

import com.github.aidan.wechat.account.dao.WechatAccountDoMapper;
import com.github.aidan.wechat.account.util.EmptyUtil;
import com.github.aidan.wechat.account.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import sun.rmi.runtime.Log;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDo {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WechatAccountDoMapper wechatAccountDoMapper;


    @Value(value = "true")
    public boolean remaining;


    public boolean accountBuffer(){


        boolean flag = tryLock("account1",1L);
        ListOperations<String,AccountVo> listOperations = redisTemplate.opsForList();
        if (flag){
            AccountVo accountVo = listOperations.rightPop("account");
            List<AccountVo> accountVoList = wechatAccountDoMapper.getAccountList(accountVo.getId());

            if (EmptyUtil.isEmpty(accountVoList)|| accountVoList.size() < 100){

                this.remaining = false;
            }

            if (EmptyUtil.isNotEmpty(accountVoList)){

                listOperations.rightPushAll("account",accountVoList);

            }
        }

        return flag;
    }


    /**
     * 该加锁方法仅针对单实例 Redis 可实现分布式加锁
     * 对于 Redis 集群则无法使用
     *
     * 支持重复，线程安全
     * @param key
     * @param timeout
     * @return
     */
    public Boolean tryLock(String key,Long timeout) {

        ValueOperations<String,Long> valueOperations = redisTemplate.opsForValue();

        return  valueOperations.setIfAbsent(key,System.currentTimeMillis()+timeout,timeout, TimeUnit.SECONDS);

    }


    /**
     * 与 tryLock 相对应，用作释放锁
     *
     * @param lockKey
     * @param clientId
     * @return
     */
    public Boolean releaseLock(String lockKey, String clientId) {

        if(lockKey == null || "".equals(lockKey)){
            return false;
        }

        ValueOperations<String,Long> valueOperations = redisTemplate.opsForValue();
        Long oldValue = valueOperations.get(lockKey);
        Long currentValue = System.currentTimeMillis();

        if (oldValue == null || oldValue < currentValue ){
            //释放锁
           return redisTemplate.delete(lockKey);
        }
        return false;
    }


}
