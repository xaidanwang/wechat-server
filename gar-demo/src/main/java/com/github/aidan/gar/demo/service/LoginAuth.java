package com.github.aidan.gar.demo.service;

/**
 * @author wang yi fei
 * @date 2019/3/1 16:18
 */

import org.springframework.stereotype.Service;

/**
 * 改接口提供用户的验证
 */
public interface LoginAuth {

    /**
     *
     * true 验证通过  false  验证失败
     * @param username
     * @param password
     * @return
     */
    boolean accountVerification(String username,String password);
}
