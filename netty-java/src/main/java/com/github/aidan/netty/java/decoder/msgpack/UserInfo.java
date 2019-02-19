package com.github.aidan.netty.java.decoder.msgpack;

import org.msgpack.annotation.Message;

/**
 * @author wang yi fei
 * @date 2019/2/18 10:51
 */

/**
 * 所有使用msgpack 编码解码的对象使用 @Message 修饰，否则无法传输
 */
@Message
public class UserInfo {

    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
