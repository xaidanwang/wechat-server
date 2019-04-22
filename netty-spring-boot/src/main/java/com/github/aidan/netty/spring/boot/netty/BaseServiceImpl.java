package com.github.aidan.netty.spring.boot.netty;

import org.springframework.stereotype.Service;

/**
 * @author wang yi fei
 * @date 2019/3/6 12:49
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public void test() {
        System.out.println("调用service服务");
    }
}
