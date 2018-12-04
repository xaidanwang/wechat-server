package com.github.aidan.wechat.account.common;

import clojure.lang.IFn;
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
    public  static boolean remaining;


    /**
     *
     * @param accountKey
     * @return true 表示数据库中还有剩余数据没有使用，false 表示数据库中所有数据都已经使用
     */
    public boolean pushNewAccount(String accountKey){

             if (getRedisPoolAccountCount(accountKey)>30){

                 return remaining;
             }

            ListOperations<String,AccountVo> listOperations = redisTemplate.opsForList();

            AccountVo accountVo = listOperations.rightPop(accountKey);

            List<AccountVo> accountVoList = null;
            if (EmptyUtil.isEmpty(accountVo)){

                this.remaining = false;
                return remaining;
            }else {
                accountVoList = wechatAccountDoMapper.getAccountList(accountVo.getId());
            }

            if (EmptyUtil.isEmpty(accountVoList)|| accountVoList.size() < 100){
                this.remaining = false;
            }

            if (EmptyUtil.isNotEmpty(accountVoList)){
                listOperations.rightPushAll(accountKey,accountVoList);
            }

        return remaining;
    }


    /**
     * 该加锁方法仅针对单实例 Redis 可实现分布式加锁
     * 对于 Redis 集群则无法使用
     *
     * 支持重复，线程安全
     * @param lockKey
     * @param timeout
     * @return
     */
    public Boolean tryLock(String lockKey,Long timeout) {

        ValueOperations<String,Long> valueOperations = redisTemplate.opsForValue();

        return  valueOperations.setIfAbsent(lockKey,System.currentTimeMillis()+timeout*1000,timeout, TimeUnit.SECONDS);

    }


    /**
     * 与 tryLock 相对应，用作释放锁
     *
     * @param lockKey
     *
     * @return
     */
    public Boolean releaseLock(String lockKey) {

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

    public AccountVo getAccount(String accountKey){

        ListOperations<String,AccountVo> listOperations = redisTemplate.opsForList();

        AccountVo accountVo = listOperations.leftPop(accountKey);

        return accountVo;
    }

    public void initAccountPool(String accountKey){

        List<AccountVo> accountVoList = wechatAccountDoMapper.getAccountList(1L);

        ListOperations<String,AccountVo> listOperations = redisTemplate.opsForList();

        listOperations.rightPushAll(accountKey,accountVoList);

    }

    public boolean valiateCount(String accountKey){

        ListOperations<String,AccountVo> listOperations = redisTemplate.opsForList();

        AccountVo accountVo = listOperations.rightPop(accountKey);

        List<AccountVo> accountVoList = null;

        if (EmptyUtil.isEmpty(accountVo)){

            accountVoList = wechatAccountDoMapper.getAccountList(1L);
        }else {
            accountVoList = wechatAccountDoMapper.getAccountList(accountVo.getId());
        }
        return true;
    }


    public Long getRedisPoolAccountCount(String accountKey){

        ListOperations<String,AccountVo> listOperations = redisTemplate.opsForList();

        return listOperations.size(accountKey);
    }


}
