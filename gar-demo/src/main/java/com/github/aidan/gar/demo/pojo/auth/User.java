package com.github.aidan.gar.demo.pojo.auth;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wang yi fei
 * @date 2019/3/1 14:42
 */
@Data
@ToString
public class User implements Serializable {

    private String username;

    private String password;
}
