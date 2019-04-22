package com.github.aidan.gar.demo.service.impl;

import com.github.aidan.gar.demo.service.LoginAuth;
import org.springframework.stereotype.Service;

/**
 * @author wang yi fei
 * @date 2019/3/1 16:24
 */
@Service
public class LoginAuthImpl implements LoginAuth {

    @Override
    public boolean accountVerification(String username, String password) {
        return true;
    }
}
